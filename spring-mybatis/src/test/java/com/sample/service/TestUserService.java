package com.sample.service;

import com.sample.exception.MallBusinessException;
import com.sample.vo.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/context-mybatis.xml")
public class TestUserService {

    @Autowired
    UserService userService;

    @Value("${user.default.deposit.point}")
    int defaultDepositPoint;

    @Test
    public void testConfig() {
        Assert.assertNotNull(userService);
    }

    User user;
    User userForEmail;
    User userForPhone;

    /*
        @Before
            - 각각의 테스트 메소드가 실행되기 전에 실행되는 메소드다.
            - 테스트 실행 전처리 작업을 수행하기 위해서 사용된다.
     */
    @Before
    public void setup() {
        // 빌더 패턴을 이용해서 초기화된 User객체 획득
        user = User.builder()
                .id("hong1212").password("zxcv1234").name("홍길동1213")
                .email("hong1212@gmail.com").phone("010-1234-1128")
                .build();

        userForEmail = User.builder()
                .id("hong2222").password("zxcv1234").name("홍길동2222")
                .email("hong1212@gmail.com").phone("010-2222-2222")
                .build();

        userForPhone = User.builder()
                .id("hong3333").password("zxcv1234").name("홍길동3333")
                .email("hong3333@gmail.com").phone("010-1234-1128")
                .build();
    }

    /*
        @After
            - 각각의 테스트 메소드가 실행된 후에 실행되는 메소드다
            - 테스트 수행을 위해서 사용했던 리소스를 반환하기 위해서 사용된다.
            - 테스트 수행을 위해서 저장소에 저장했던 값들을 초기화시키기 위해서 사용된다.
     */
    @After
    public void teardown() {
        // 테스트 수행과정에 데이터베이스에 저장했던 테스트 사용자 정보를 전부 삭제한다.
        userService.removerUser("hong1212");
        userService.removerUser("hong2222");
        userService.removerUser("hong3333");

    }

    @Test
    public void testAddNewUser() {
        // 신규 사용자 정보 저장
        userService.addNewUser(user);
        // 새로 저장된 사용자 정보 조회
        User savedUser = userService.getUserDetail(user.getId());

        // 새로 저장된 사용자 정보가 회원가입 요구사항에 맞게 값이 저장되었는지 체크
        Assert.assertNotNull(savedUser);
        Assert.assertEquals("hong1212", savedUser.getId());
        Assert.assertEquals("active", savedUser.getStatus());
        Assert.assertEquals(defaultDepositPoint, savedUser.getPoint());
    }

    @Test(expected = MallBusinessException.class)
    public void testAddNewUserExistId() {
        // 신규 사용자 저장
        userService.addNewUser(user);
        // 동일한 아이디를 가진 신규 사용자 저장
        userService.addNewUser(user);
    }

    @Test(expected = MallBusinessException.class)
    public void testAddNewUserExistEmail() {
        // 신규 사용자 저장
        userService.addNewUser(user);
        // 동일한 아이디를 가진 신규 사용자 저장
        userService.addNewUser(userForEmail);
    }

    @Test(expected = MallBusinessException.class)
    public void testAddNewUserExistPhone() {
        // 신규 사용자 저장
        userService.addNewUser(user);
        // 동일한 아이디를 가진 신규 사용자 저장
        userService.addNewUser(userForPhone);
    }
}
