package com.project.admin.model.network.request;

import com.project.admin.model.enumclass.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//header을 제외한 data에 들어갈 정보를 다루는 클래스
//client = FE 측에서 처리해달라고 입력한 값을 저장하는 클래스
public class UserApiRequest {

    private Long id;
    private String account;
    private String password;
    private UserStatus status;
    private String email;
    private String phoneNumber;

    //아래는 서버에서 관리되는게 사실상 맞는 정보들이다.
    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt;
}
