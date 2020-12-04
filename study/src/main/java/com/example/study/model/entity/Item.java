package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor //모든 변수를 다 받아 저장하는 생성자
@NoArgsConstructor //기본 생성자
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private Integer price;
    private String content;

    // 1 : Many
    // lazy = select * from item where id = ?
    // eager = 연관관계가 있는 테이블을 조인시킨다.
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "item")
    private List<OrderDetail> orderDetailList;
}
