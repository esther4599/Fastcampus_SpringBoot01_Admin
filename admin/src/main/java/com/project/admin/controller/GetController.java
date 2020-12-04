package com.project.admin.controller;

import com.project.admin.model.SearchParam;
import com.project.admin.model.network.Header;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") //전에 study에서 사용하던 주소와 겹치면 양측 다 엘러 발생

public class GetController {
    @RequestMapping(method = RequestMethod.GET, path = "/getMethod") //localhost:8080/api/GetMethod 호출시 아래의 함수가 실행
    public String getRequest() {
        return "hi get method";
    }

    @GetMapping("/getParameter") //localhost:8080/getMethod?id=1234&password=abcd //client가 입력한 데이터를 받음
    public String getParaMeter(@RequestParam String id, @RequestParam(name = "password") String pwd){
        String password = "bbb";

        System.out.println(id);
        System.out.println(pwd);
        System.out.println(password+"\n"); //해당 코드에서 개발자가 작성한 변수를 출력 == bbb 출력

        if(id.equals("1234") && pwd.equals("abcd"))
            return "success to login";
        else return "fail to login";
    }

    @GetMapping("/getMultiParameter") //localhost:8080/api/getMultiParameter?account=abcd&email=study@gmail.com&age=10
    public SearchParam getMultiParameter(SearchParam searchParam){
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());

        return searchParam;
    }

    @GetMapping("/header")
    public Header getHeader(){

//        { "transactionTime": null, "resultCode": "OK", "description": "OK" }
        return Header.builder().resultCode("OK").description("OK").build();
    }
}
