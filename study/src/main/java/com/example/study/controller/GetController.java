package com.example.study.controller;

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController //spring에게 해당 클래스가 controller임을 인식 시키는 지시자

//damin 프로젝트와 충돌로 코드 변경. 주석 설명은 바로 아래의 코드 기반
//@RequestMapping("/api") //localhost:8080/api 까지 주소가 맵핑이 됨
@RequestMapping("/test")

/* Get Method
* 주소 창에 파라메터가 노출된다.
* 브라우저에서 ㅈ주소에 대한 캐시가 이루어짐으로 이를 활용해 정보를 얻을때 사용한다.
*/
public class GetController {
    @RequestMapping(method = RequestMethod.GET, path = "/getMethod") //localhost:8080/api/GetMethod 호출시 아래의 함수가 실행
    public String getRequest() {
        return "hi get method";
    }

    @GetMapping("/getParameter") //localhost:8080/getMethod?id=1234&password=abcd //client가 입력한 데이터를 받음
    //아래의 파라미터 중 name ==> client가 url창에 입력할 변수의 이름을 의미한다. **client에게는 password, 개발자에게는 pwd**
    public String getParaMeter(@RequestParam String id, @RequestParam(name = "password") String pwd){
        String password = "bbb"; //개발자가 지정한 변수. 위의 파라미터 속 password와는 무관하다.

        System.out.println(id);
        System.out.println(pwd);
        System.out.println(password+"\n"); //해당 코드에서 개발자가 작성한 변수를 출력 == bbb 출력

        if(id.equals("1234") && pwd.equals("abcd"))
            return "success to login";
        else return "fail to login";
    }

    //localhost:8080/api/getMultiParameter?account=abcd&email=study@gmail.com&age=10
    /*사용자가 입력하는 파라미터의 갯수가 많다면 해당 파라미터들을 모두 함수로 일일이 작성하기는 번거롭다.
    * 위의 예시의 파라미터를 받는 함수를 작성한다면
    * public String multiParameter(@RequestParam String account, @RequestParam String email, @RequestParam int page){}
    * 가 된다. 이를 새로운 객체를 만들어 관리할 수 있다. => searchParam 객체*/
    @GetMapping("/getMultiParameter") //localhost:8080/api/getMultiParameter?account=abcd&email=study@gmail.com&age=10
    public SearchParam getMultiParameter(SearchParam searchParam){
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());

        return searchParam;
        //html에서 네트워크 통신 표준 규격은 json이다. spring에서 자동으로 객체를 json으로 변환해주어 아래와 같은 출력이 가능하다.
        //{"account":"abcd","email":"study@gmail.com","page":0}
    }
}
