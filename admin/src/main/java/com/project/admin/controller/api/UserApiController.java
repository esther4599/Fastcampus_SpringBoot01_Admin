package com.project.admin.controller.api;

import com.project.admin.controller.CRUDController;
import com.project.admin.model.entity.User;
import com.project.admin.model.network.Header;
import com.project.admin.model.network.request.UserApiRequest;
import com.project.admin.model.network.response.UserApiResponse;
import com.project.admin.model.network.response.UserOrderInfoApiResponse;
import com.project.admin.service.UserApiLogicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j //simple logging for facade for java
// ^ log를 남겨서 객체의 데이터를 확인할 수 있도록 하는 기능

@RestController
@RequestMapping("/api/user")
//빠지면 안되는 CRUD 함수의 경우 인터페이스를 만들어둔다.
@RequiredArgsConstructor
public class UserApiController extends CRUDController<UserApiRequest, UserApiResponse, User> {
    @Autowired
    UserApiLogicService userApiLogicService;

    @GetMapping("/{id}/orderInfo")
    public Header<UserOrderInfoApiResponse> orderInfo(@PathVariable Long id){
        return userApiLogicService.orderInfo(id);
    }
}

// 코드 리팩토리 전 코드
//    @Autowired
//    private UserApiLogicService userApiLogicService;
//
//    @PostConstruct
//    public void init() {
//        this.baseService = userApiLogicService;
//    }
//}



//  로그 사용예시 코드
//    public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> request) {
//
//        /* @Slf4j 기능
//        * log.info("{}, {}", request, "ABC");
//        * 위의 코드 결과 "request.toString(), ABC" 라는 로그가 파일에 남는다.
//        * */
//
//        log.info("{}", request); //아래는 출력되는 로그 형태
//        //c.p.a.controller.api.UserApiController : Header(transactionTime=2019-07-31T22:57:56.692, resultCode=null, description=OK, data=UserApiRequest(id=null, account=createTest01, password=createTest01PW, status=UNREGISTERED, email=createTest01@gmail.com, phoneNumber=010-1234-5678))
//        return userApiLogicService.create(request);
//    }
