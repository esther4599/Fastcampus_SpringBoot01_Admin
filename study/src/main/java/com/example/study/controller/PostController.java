package com.example.study.controller;

/*
- Post method
 주소창에 파라미터가 노출되지 않는다. => 보안^
 => 바디를 찾아보면 나오기는 한다.

- post가 발생하는 경우
 = html의 <form>, ajax 검색
*/

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController //컨트롤러임을 알리기
//damin 프로젝트와 충돌로 코드 변경. 주석 설명은 바로 아래의 코드 기반
//@RequestMapping("/api")
// /api 입력시 해당 클래스를 찾아본다.
//class 단위의 경우 맵핑주소가 동일해도 오류가 발생하지 않는다.
//메소드의 경우 오류 발생
@RequestMapping("/test")


public class PostController {
    //json, xml, multipart-form, text-palin 등을 설정해줄 수 있다. (아래 파라미터 중 produces가 이에 해당한다.)
    @PostMapping(value = "/postMethod") //localhost:8080/api/postMethod
    //, produces = {"application-json"})
    //RequestBody = http 통신에서 body에서 해당 데이터를 받아오겠다는 의미
    //아래의 메소드를 테스트하기 위해서 개발 툴을 이용한다. chrome의 rest client 확장 도구 중 talented에서제작한 api
    //해당 확장 프로그램에서 데이터의 타입은 "json"을 기반으로 작성함
    public SearchParam postMethod(@RequestBody SearchParam searchParam){
        return searchParam;
    }


    /*
     * http - put/patch method
     * post와 마찬가지로 body에 데이터가 들어있으며, 주로 업데이트에 사용한다.
     * 데이터 베이스의 특정 데이터를 가져와 올리는 경우
     * 둘 다 rest api에서 주소를 할당해 사용하지는 않는다.
     */

    @PutMapping("/putMethod")
    public void put(){ }

    @PatchMapping("/patchMethod")
    public void patch(){ }

    /*
    * http - delete method
    * get과 마찬가지로 주소에 파라미터가 들어가며, 데이터를 삭제할때 사용한다.
    */

    /* Rest의 개념
    * http 프로토콜에 있는 method를 활용한 아키텍처 스타일이다.
    * http method를 통해서 resource를 처리한다.
    * CRUD를 통한 Resource 조작을 할때 사용한다.태
    *
    * http method - 동작(resource = 데이터) - url 형
    * get - 조회(select / read) - /user/{id}
    * post - 생성(create) - /user
    * put/patch - 수정(update / create) - /user
    * delete - 삭제(delete) - /user/{1}
    */
}

