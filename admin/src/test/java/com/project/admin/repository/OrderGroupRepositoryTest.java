package com.project.admin.repository;

import com.project.admin.AdminApplicationTests;
import com.project.admin.model.entity.OrderGroup;
import com.project.admin.model.enumclass.OrderType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderGroupRepositoryTest extends AdminApplicationTests {

    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Test
    public void create(){
        //userId 외래키
        OrderGroup orderGroup = new OrderGroup(0L, "COMPLETE",
                OrderType.ALL, "서울시 종로구", "이순신", "CARD", BigDecimal.valueOf(900000),
                1, LocalDateTime.now().minusDays(2), LocalDateTime.now(),
                LocalDateTime.now(), "AdminServer", null, null, null, null);

        OrderGroup newOrderGroup = orderGroupRepository.save(orderGroup);
        Assert.notNull(newOrderGroup, "error");
    }
}
