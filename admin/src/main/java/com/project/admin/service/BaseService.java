package com.project.admin.service;

import com.project.admin.ifs.CRUDInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component //Autowired를 받기 위해서 지정
public abstract class BaseService<Req,Res,Entity> implements CRUDInterface<Req,Res> {

    @Autowired(required = false) //안에 설정. 빈 객체가 존재하지 않더라도 예외처리를 발생시키지 않는다.
    protected JpaRepository<Entity, Long> baseRepository;

    /* 위의 코드를 item 객체 기준으로 설명하면
       JpaRepository<Item,Long>으로 설정된 것으로 해당 설정으로 만들어둔
       ItemRepository를 자동으로 가져온다.
     */
}
