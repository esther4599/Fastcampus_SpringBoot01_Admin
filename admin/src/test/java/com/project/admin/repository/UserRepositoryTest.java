package com.project.admin.repository;

import com.project.admin.AdminApplicationTests;
import com.project.admin.model.entity.User;
import com.project.admin.model.enumclass.UserStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends AdminApplicationTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void create(){ //Hibernate: insert into user (account, created_at, created_by, email, password, phone_number, registered_at, status, unregistered_at, updated_at, updated_by) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)

        String account = "test03";
        String password = "test03pw";
        UserStatus status = UserStatus.REGISTERED;
        String email = "test03@gmail.com";
        String phoneNumber = "010-3333-3333";
        LocalDateTime registeredAt = LocalDateTime.now();
        //LocalDateTime createdAt = LocalDateTime.now();
        //String createdBy = "AdminServer";

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);
        //user.setCreatedAt(createdAt);
        //user.setCreatedBy(createdBy);

        User newUser = userRepository.save(user);
        Assert.notNull(newUser, "error");
    }

    @Test
    @Transactional
    public void read(){
        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-1111");
        Assert.notNull(user, "error");

        //@Accessors(chain = true) 예시
//        user.setEmail("바").setStatus("보");
//        User u = new User().setAccount("먀").setEmail("옹");

        user.getOrderGroupList().stream().forEach(orderGroup -> {
            System.out.println("=========주문묶음========");
            System.out.println("수령인 : " + orderGroup.getRevName());
            System.out.println("수령지 : " + orderGroup.getRevAddress());
            System.out.println("총 금액 : " + orderGroup.getTotalPrice());

            System.out.println("========주문 상세========");
            orderGroup.getOrderDetailList().stream().forEach(orderGroupDetail -> {
                System.out.println("주문 상품 : " + orderGroupDetail.getItem().getName());
                System.out.println("상품 브랜드 : " + orderGroupDetail.getItem().getBrandName());
                System.out.println("제조 회사 콜센터 번호 : " + orderGroupDetail.getItem().getPartner().getCallCenter());
                System.out.println("주문 상태 : " + orderGroupDetail.getStatus());
                System.out.println("도착 예정 일자 : " + orderGroupDetail.getArrivalDate());
            });

        });
    }

    @Test
    public void update(){
        Optional<User> user = userRepository.findById(2L); //파라미터가 2L인 이유는 id 타입이 Long이기 때문이다.
        user.ifPresent(selectedUser -> {
            selectedUser.setAccount("ppp");
            selectedUser.setUpdatedAt(LocalDateTime.now());
            selectedUser.setUpdatedBy("update method");

            userRepository.save(selectedUser);
        });
    }

    @Test
    @Transactional
    public void delete(){
        Optional<User> user = userRepository.findById(2L);
        Assert.isTrue(user.isPresent());

        user.ifPresent( selectedUser -> {
            userRepository.delete(selectedUser);
        });

        Optional<User> check = userRepository.findById(2L);
        Assert.isTrue(!check.isPresent());
    }
}
