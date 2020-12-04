package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

//아래 Item과 User 각각에 toString함수가 만들어져 충돌이 일어난다.
@ToString(exclude = {"user","item"}) //두 변수에 제외. 상호참조를 해제한다?
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime orderAt;

    //orderDetail 입장에서 생각 = 앞 특징이 orderDetail 특징
    //orderDetail : user = N : 1
    //관계의 다른 객체를 변수로 선언
    @ManyToOne
    private User user; //private Long userId; //연관관계 설정 전

    //orderDetail : item = N : 1
    @ManyToOne
    private Item item ; //private Long itemID; //연관관계 설정 전
}
