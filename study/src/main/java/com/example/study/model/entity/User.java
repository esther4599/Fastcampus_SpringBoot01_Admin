package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity //해당 class를 테이블로 받는다.
//@Table(name = "user")
//테이블의 이름과 클래스의 이름이 서로 동일해 실제로 작성해줄 필요는 없다.

@Data //lombok 이용해 기본적인 get/set 메소그 작성
@AllArgsConstructor //lombok 이용. 모든 매개변수를 입력받는 생성자 작성
@NoArgsConstructor //기본 생성자를 만들어준다.
public class User {

    //@Column(name = "id") //변수명과 column명 동일시 생략가능
    @Id //primary key에 붙여야하는 태그
    @GeneratedValue(strategy =GenerationType.IDENTITY) //주키의 값을 생성하 전략
    /* GeneratedValue 전략 4가지
    * identity: DB의 identity 컬럼을 이용
    * auto: 자동 생성
    * sequence: DB의 sequence 컬럼을 이용
    * table: 유일성이 보장된 데이터베이스 테이블을 이용
    * */
    private long id; //bigint = long

    private String account;
    private String email;

    //jpa에서는 자동으로 디비의 snake case를 camel case로 변경해준다.
    private String phoneNumber;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

    //oneToMany
    //이 객체 하나에 여러개의 orderDetail이 연결 = List 처리
    //fetchType =
    //mappedBy = 상대 객체에서 본 객체를 저장한 이름 설정
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<OrderDetail> orderDetailList;
}
