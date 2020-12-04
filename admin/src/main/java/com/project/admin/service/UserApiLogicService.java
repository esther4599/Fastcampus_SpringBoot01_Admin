package com.project.admin.service;

import com.project.admin.model.entity.OrderGroup;
import com.project.admin.model.entity.User;
import com.project.admin.model.enumclass.UserStatus;
import com.project.admin.model.network.Header;
import com.project.admin.model.network.Pagination;
import com.project.admin.model.network.request.UserApiRequest;
import com.project.admin.model.network.response.ItemApiResponse;
import com.project.admin.model.network.response.OrderGroupApiResponse;
import com.project.admin.model.network.response.UserApiResponse;
import com.project.admin.model.network.response.UserOrderInfoApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service //서비스로 동작하게 된다.
public class UserApiLogicService extends BaseService<UserApiRequest, UserApiResponse, User> {

    @Autowired
    private OrderGroupApiLogicService orderGroupApiLogicService;

    @Autowired
    private ItemApiLogicService itemApiLogicService;

    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

        // 1. request data를 가져온다.
        UserApiRequest userApiRequest = request.getData();

        // 2. user 생성
        User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status(UserStatus.REGISTERED) // "REGISTERED", 변수를 직접 입력하면 error가 발생할 수 있어 enum으로 관리한다.
                .phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();
        User newUser = baseRepository.save(user);

        // 3. 생성된 data를 기준으로 user api response를 만들어서 return
        // userApiResponse -> Header<userApiResponse>
        return Header.OK(response(newUser));
    }

    @Override
    public Header<UserApiResponse> read(Long id) {

        // 1. id -> repository : getOne || getById
        // 2. user -> userApiResponse return

        /* map: 연결이 되었다면 연결된 객체를 user로 받아서
                -> 에 연결된 함수를 실행한다.
           orElseGet: 위의 상태가 아닌 경우 화살표에 연결된 함수를 실행해 return
        */
        return baseRepository.findById(id)
                .map(user -> Header.OK(response(user)))
                .orElseGet( () ->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
        // 1. data 가져오기
        UserApiRequest userApiRequest = request.getData();

        // lambda 를 활용한 코드
        return baseRepository.findById(userApiRequest.getId()) // 2. id를 가지고 user data 찾기
                .map(user -> {
                    // 찾아온 객체에 요청된 데이터를 덮어쓰기
                    user.setAccount(userApiRequest.getAccount())
                            .setPassword(userApiRequest.getPassword())
                            .setStatus(userApiRequest.getStatus())
                            .setPhoneNumber(userApiRequest.getPhoneNumber())
                            .setEmail(userApiRequest.getEmail())
                            .setRegisteredAt(userApiRequest.getRegisteredAt())
                            .setUnregisteredAt(userApiRequest.getUnregisteredAt());
                    return user;
                })
                .map(user -> baseRepository.save(user)) // 3. update == db 반영
                .map(updatedUser -> Header.OK(response(updatedUser))) // 4. userApiResponse return
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        // id -> repository -> user
        return baseRepository.findById(id)
                .map(user -> {
                    baseRepository.delete(user); // repository -> delete
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("데이터 없음")); //response return
    }

    private UserApiResponse response(User user){
        // user -> userApiResponse
        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword()) //todo 암호화, 길이
                .email(user.getEmail())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();

        return userApiResponse;
    }

    @Override
    public Header<List<UserApiResponse>> search(Pageable pageable){

        // page -> list<UserApiResponse>
        Page<User> users = baseRepository.findAll(pageable);
        List<UserApiResponse> userApiResponseList = users.stream()
                .map( user -> response(user))
                .collect(Collectors.toList()); //자료형 변환

        Pagination pagination = Pagination.builder()
                .totalPages(users.getTotalPages())
                .totalElement(users.getTotalElements())
                .currentPage(users.getNumber())
                .currentElement(users.getNumberOfElements())
                .build();

        // list<UserApiResponse> -> header<list<UserApiResponse>>
        return Header.OK(userApiResponseList,pagination);
    }

    public Header<UserOrderInfoApiResponse> orderInfo(Long id) {

        //사용자
        User user = baseRepository.getOne(id);
        UserApiResponse userApiResponse = response(user);

        //orderGroup //response 접근제한자 public 으로 변경
        List<OrderGroup> orderGroupList = user.getOrderGroupList();
        List<OrderGroupApiResponse> orderGroupApiResponseList = orderGroupList.stream()
                .map( orderGroup -> {

                    OrderGroupApiResponse orderGroupApiResponse = orderGroupApiLogicService.response(orderGroup);

                    // item api response 만들어주기
                    List<ItemApiResponse> itemApiResponseList = orderGroup.getOrderDetailList().stream() //각 order group 별 detailList 가져오기
                            .map(detail -> detail.getItem()) //detail list의 각 detail 가져오기
                            .map( item -> itemApiLogicService.response(item)) //해당 detail에 있는 하나의 item 가져와 item response 로 변경
                            .collect(Collectors.toList());

                    orderGroupApiResponse.setItemApiResponseList(itemApiResponseList);
                    return orderGroupApiResponse;
                })
                .collect(Collectors.toList());

        userApiResponse.setOrderGroupApiResponseList(orderGroupApiResponseList);

        UserOrderInfoApiResponse userOrderInfoApiResponse = UserOrderInfoApiResponse.builder()
                .userApiResponse(userApiResponse).build();

        return Header.OK(userOrderInfoApiResponse);
    }
}
