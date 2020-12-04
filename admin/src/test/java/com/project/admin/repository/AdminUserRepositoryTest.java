package com.project.admin.repository;

import com.project.admin.AdminApplicationTests;
import com.project.admin.model.entity.AdminUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public class AdminUserRepositoryTest extends AdminApplicationTests {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Test
    public void create(){
        AdminUser adminUser = new AdminUser();

        adminUser.setAccount("AdminUser03");
        adminUser.setPassword("AdminUser03pw");
        adminUser.setStatus("REGISTERED"); //오타가 날 수 있는 부분으로 후에 enum 타입으로 관리한다.
        adminUser.setRole("PARTNER");
//        adminUser.setCreatedAt(LocalDateTime.now());
//        adminUser.setCreatedBy("AdminServer");

        //insert 문
        AdminUser newAdminUser = adminUserRepository.save(adminUser);
        Assert.notNull(newAdminUser, "error");

        //update 문
        newAdminUser.setAccount("CHANGE");
        adminUserRepository.save(newAdminUser);
    }
}
