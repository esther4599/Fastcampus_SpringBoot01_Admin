package com.project.admin.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

//class 대신에 enum
@Getter
@AllArgsConstructor
public enum UserStatus {
    REGISTERED(0, "가입", "사용자 등록상태"), //id, 한글로 표현할 값, 설명
    UNREGISTERED(1, "해지", "사용자 해지상태");

    private Integer id;
    private String title;
    private String description;
}
