package com.project.admin.repository;

import com.project.admin.AdminApplicationTests;
import com.project.admin.model.entity.Item;
import com.project.admin.model.enumclass.ItemStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ItemRepositoryTest extends AdminApplicationTests {
    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void create(){
        //파트너 아이디 = 외래키
        Item item = new Item(0, ItemStatus.REGISTERED, "삼성 노트북", "노트북 A100",
                "2019년형 노트북", BigDecimal.valueOf(900000), "삼성", LocalDateTime.now(), null,
                LocalDateTime.now(), "Partner01", null, null, null, null);

        Item newItem = itemRepository.save(item);
        Assert.notNull(newItem, "error");
    }
}
