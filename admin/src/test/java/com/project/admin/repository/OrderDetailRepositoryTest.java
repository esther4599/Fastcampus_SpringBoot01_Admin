package com.project.admin.repository;

import com.project.admin.AdminApplicationTests;
import com.project.admin.model.entity.OrderDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderDetailRepositoryTest extends AdminApplicationTests {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void create(){
        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setStatus("WAITING");

        //현재 날짜에서 이틀 추가
        orderDetail.setArrivalDate(LocalDateTime.now().plusDays(2));

        orderDetail.setQuantity(1);

        //bigDecimal 값 입력하기
        orderDetail.setTotalPrice(BigDecimal.valueOf(900000));

        orderDetail.setCreatedAt(LocalDateTime.now());

        orderDetail.setCreatedBy("AdminServer");

        //외래키
        //orderDetail.setOrderGroupId(1L); //어떤 장바구니에
        //orderDetail.setItemId(1L); //어떤 상품을 담는다.

        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);
        Assert.notNull(newOrderDetail, "error");
    }
}
