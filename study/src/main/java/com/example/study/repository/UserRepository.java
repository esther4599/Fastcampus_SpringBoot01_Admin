package com.example.study.repository;

import com.example.study.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
//상속을 받을때 클래스명과 기본키의 타입을 파라미터로 넘긴다.
public interface UserRepository extends JpaRepository<User, Long> {

    // select * from user where account = ? << test03, test04 ...
    Optional<User> findByAccount(String account);

    Optional<User> findByEmail(String email); //select * from user where email = ?

    // select * from user where account = ? and email = ?
    // 파라미터는 변수명과 관계없이 입력된 순서대로 처리된다.
    Optional<User> findByAccountAndEmail(String account, String email);
}
