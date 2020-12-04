package com.project.admin.repository;

import com.project.admin.AdminApplicationTests;
import com.project.admin.model.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Optional;

public class CategoryRepositoryTest extends AdminApplicationTests {
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    //Hibernate: insert into category (created_at, created_by, title, type, updated_at, updated_by) values (?, ?, ?, ?, ?, ?)
    public void create(){
        String type = "COMPUTER";
        String title = "컴퓨터";
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        //생성
        Category category  = new Category();
        category.setType(type);
        category.setTitle(title);
        category.setCreatedAt(createdAt);
        category.setCreatedBy(createdBy);

        //저장
        Category newCategory = categoryRepository.save(category);

        //저장 여부 확인
        Assert.notNull(newCategory, "error");
        //아래는 저장된 데이터가 입력한 데이터와 동일한지를 확인하는 코드
        Assert.isTrue(newCategory.getType().equals(type), "error");
        Assert.isTrue(newCategory.getTitle().equals(title), "error");
    }

    @Test
    public void read(){
        String type = "COMPUTER";

        Optional<Category> category = categoryRepository.findByType(type);
        // select * from category where type = "COMPUTER"

        category.ifPresent( c -> {
            Assert.isTrue(c.getType().equals(type), "error");
            System.out.println(c);
        });
    }
}
