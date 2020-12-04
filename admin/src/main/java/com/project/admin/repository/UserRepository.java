package com.project.admin.repository;

import com.project.admin.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //핸드폰 번호를 이용하고 아이디로 역순 정렬해 user 찾기
    User findFirstByPhoneNumberOrderByIdDesc(String phoneNumber);
}
