package com.example.study.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/* lombok 설치
* help -> pugins -> market place에서 lombok 검색 후 설치
* gradle에 라이브러리를 디펜던시에 걸어주어야 사용이 가능하다.
* lombok을 사용하면 이전의 getter/setter을 사용한 코드가 인식이 되지 않는 경우가 발생한다.
* -> 중간에 annotation processors에서 enable 설정해주면 제대로 작동한다.
*/

@Data // 변수에 대한 get/set이 자동으로 추가된다.
@AllArgsConstructor //모든 매개변수를 갖는 생성자를 생성해준다.
public class SearchParam{
    private String account;
    private String email;
    private int page;
}

/* lombok 사용전 코드

public class SearchParam {
    //저장할 데이터
    private String account;
    private String email;
    private int page;

    public SearchParam(){}

    //상단의 code->generate 이용하여 getter $ setter 작성 (드래그로 여러 변수 선택 가능)
    public String getAccount() { return account; }

    public void setAccount(String account) { this.account = account; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public int getPage() { return page; }

    public void setPage(int page) { this.page = page; }
}
*/