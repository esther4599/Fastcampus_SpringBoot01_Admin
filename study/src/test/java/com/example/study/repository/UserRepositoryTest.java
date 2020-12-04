package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends StudyApplicationTests {
    @Autowired
    private UserRepository userRepository;
    /* Autowired??
    * spring의 가장 큰 장점이자 디자인 패턴으로 DI라고 한다.
    * DI는 dependency injection으로 의존성 주입을 의미한다.
    *
    * private UserRepository userRepository = new UserRepository();
    * public void create(){
        userRepository.save()
        userRepository.count()
    }
    *
    * 위와 같은 의존성이 필요한 부분을 spring이 알아서 관리하는 특징을 의미한다.
    * 따로 위와 같은 코드를 작성하지 않아도 spring이 알아서 이를 관리한다.
    * */

    @Test
    public void create(){
        /* String sql = insert into user (%s, %s, %d ...) values (account, ...)
         * 위와 같은 query문을 이용해 데이터베이스에 접근하는 것이 아니라 Object를
         * 활용해 데이터베이스에 접근할 수 있도록 한다.
         * */

        User user = new User();
        /* 위의 코드는 왜 DI를 이용해 관리하지 않을까?
         * DI의 기본 핵심은 singleton pattern 이다.
         * => singleton pattern: 인스턴스가 단 한개만 생성되는 특징을 의미한다.
         * User의 경우 사용자마다 다른 정보를 갖음으로 사용자마다 각각의 인스턴스를 만들어야한다.
         * 따라서 DI로 관리하지 않는다.
         * ex) user('바이든'), user('트럼프')
         * */

        //생성한 Object에 데이터를 저장
        //데이터를 저장하는 경우 데이터의 특징을 잘 확인해야 한다.
        //user.setId(2L); //id의 경우 autoincrement로 자동으로 할당되는 값. 작성하면 안된다.
        user.setEmail("testUser@gmail.com");
        user.setPhoneNumber("010-1111-1111");

        //not null 값
        user.setAccount("testUser");
        user.setCreatedAt(LocalDateTime.now()); //현재시간을 입력
        user.setCreatedBy("admin");

        //작성한 Object를 데이터베이스에 저장
        ////user를 DB에 저장. 저장된 정보를 객체 형태로 return
        User newUser = userRepository.save(user);
        System.out.println(newUser); //id 값도 저장되어있다.

        /* JPA 사용시 선택할 수 있는 설정
        * spring.jpa.show-sql=true //resources의 application에서 설정
        * 로그에서 sql 쿼리문을 보여주는 설정
        * Hibernate: insert into user (account, created_at, created_by, email, phone_number, updated_at, updated_by) values (?, ?, ?, ?, ?, ?, ?)
         * */
    }

    @Test //데이터 베이스의 read(select) 관련 함수는 find가 있다.
    @Transactional //연관 관계 설정하면서 추가. request 하나에 하나의 트랙잭션만 잡히는 게 정상이나 Test에서는 예외상황이 발생하기도 한다.
    public void read(){
        //userRepository.findAll()
        //디비에 있는 user 테이블을 모두 가져오겠다는 의미

        //primary key를 이용해 하나의 튜플을 찾는 함수
        //아래의 함수의 return 값은 Optional 이다.
        //Optional?? = option의 의미는 return값이 있을 수도 없을 수도 있다는 의미이다.
        //Optional<User> user = userRepository.findById(1L); //파라미터가 ?L인 이유는 id 타입이 Long이기 때문이다.

        //JPAQuery method 실습 후 코드
        Optional<User> user = userRepository.findByAccount("testUser");

        //user에 객체가 존재한다면 ()안에 내용을 실행
        // -> 좌변의 selectedUser은 user에 저장된 객체에 이름을 붙힌것이다.
            //get~~() 메소드를 통해 원하는 속성값을 가져올 수 있다.
        user.ifPresent(selectedUser -> {
//            System.out.println("selected user : " + selectedUser);
//            System.out.println("selected user email = " + selectedUser.getEmail());

            //연관관계 설정 후 주문 내역 데이터를 읽어오기.
            selectedUser.getOrderDetailList().stream().forEach( detail -> {
                System.out.println(detail.getUser());
                System.out.println(detail.getItem()); //오류 발생 => lombok 사용시 문제 발생
            });
        });
    }

    @Test
    public void update(){
        Optional<User> user = userRepository.findById(2L); //파라미터가 2L인 이유는 id 타입이 Long이기 때문이다.
        user.ifPresent(selectedUser -> {
            //primary key를 이용해 데이터를 수정하고 있다. 만약 여기서 아래와 같은 코드를 추가하면
            //id가 3인 튜플의 데이터가 수정된다.
            //selectedUser.setId(3L);

            selectedUser.setAccount("ppp");
            selectedUser.setUpdatedAt(LocalDateTime.now());
            selectedUser.setUpdatedBy("update method");

            //업데이트를 할 경우 원하는 튜플의 데이터를 변경한 후 반드시 다시 저장해야한다.
            userRepository.save(selectedUser);
            //Hibernate: update user set account=?, created_at=?, created_by=?, email=?, phone_number=?, updated_at=?, updated_by=? where id=?
            //위와 같은 쿼리문이 나오는 이유는 기존의 값들을 갖는 객체에서 우리가 원하는 정보를 변경한 후
            //다시 그 객체의 모든 정보를 다시 저장하기 때문이다. where id=? => id 값을 찾아서 변경
        });
    }

    //@DeleteMapping("/api/user") //실제로 코딩하게 된다면 붙게될 주소
    //@RequestParam Long id //위와 같은 경우 파라미터
    @Test
    @Transactional //메소드는 모두 실행이되나 roll back 해준다. //결국 실제 데이터베이스에서 데이터가 삭제되지는 않는다.
    public void delete(){
        Optional<User> user = userRepository.findById(2L);

        //데이터를 삭제하기 위해서는 반드시 데이터가 존재해야 하므로 데이터가 존재하는지 확인하는 코드
        //아래 isTrue는 사라질 위험이 있는 메소드... 어떤 메소드로 대체할 수 있을까...?
        Assert.isTrue(user.isPresent()); //true 값이 파라미터로 주어지지 않으면 사용자가 정의한 예외를 발생시킨다.

        user.ifPresent( selectedUser -> {
            userRepository.delete(selectedUser); //홰당 메소드는 return값이 없다.
            //delete from user where id=?
        });

        Optional<User> check = userRepository.findById(2L);
        //아래는 삭제 여부를 확인하기 위한 코드 1
//        if(check.isPresent()){ System.out.println("??"); }
//        else System.out.println("delete data successfully"); //출력됨

        //아래는 삭제 여부를 확인하기 위한 코드 2
        Assert.isTrue(!check.isPresent());
    }
}
