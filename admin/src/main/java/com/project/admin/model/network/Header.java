package com.project.admin.model.network;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Header <T> {

    //api 통신시간
    private LocalDateTime transactionTime; // 다양하게 형식을 맞출 수 있지만 LocalDatetime으로 설정

    //api 응답 코드
    private String resultCode;

    //api 부가 설명
    private String description;

    //상황에 따라 달라지는 data가 저장될 변수
    private T data;

    //페이징 정보를 담는다.
    private Pagination pagination;

    /* 아래는 정상적인 호출인 경우 OK를 아닌 경우 error을 출력하는 함수
       데이터가 있는 경우 데이터도 return */

    //ok
    public static <T> Header<T> OK(){
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK").description("OK").build();
    }

    //data OK
    public static <T> Header<T> OK(T data){
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK").description("OK")
                .data(data).build();
    }

    //error 메소드의 경우 왜, 어떤 에러가 발생헸는지 이유를 저장하는 변수다.
    public static <T> Header<T> ERROR(String description){
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("ERROR")
                .description(description).build();
    }

    //pagination OK
    //data OK
    public static <T> Header<T> OK(T data, Pagination pagination){
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK").description("OK")
                .data(data)
                .pagination(pagination).build();
    }
}

//{ "transactionTime": null, "resultCode": "OK", "description": "OK" }
/* 호출 결과가 camel Case 로 출력된다. 이 부분을 snake case로 바꾼다.
*  1. @JasonProperty("이름") //jason 형태로 만들어질때 이름을 지정하는 방식
*  2. application.properties에 spring.jackson.property-naming-strategy=SNAKE_CASE 추가
*
* => 결과
* { "transaction_time": null, "result_code": "OK", "description": "OK", "data": null }
* */

//@JsonInclude() => jason으로 변경될 때 포함시킬 데이터 설정 -- 다음 기회에.....