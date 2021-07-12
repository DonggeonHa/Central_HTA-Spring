package com.sample.service;

import com.sample.exception.MallBusinessException;
import com.sample.mapper.UserMapper;
import com.sample.vo.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/*
    @Service
        - <context:component /> 설정으로 클래스가 자동으로 스캔되고, 객체 생성 후 스프링의 빈으로 등록되게 한다.
 */
@Service
public class UserService {

    @Value("${user.default.deposit.point}")
    private int defaultDepositPoint;

    @Autowired
    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public void addNewUser(User user) {
    	User savedUser = userMapper.getUserById(user.getId());
    	if (savedUser != null) {
    		throw new MallBusinessException("[" + user.getId() + "] 는 사용중인 아이디입니다.");
    	}
    	savedUser = userMapper.getUserByEmail(user.getEmail());
    	if (savedUser != null) {
    	    throw new MallBusinessException("[" + user.getEmail() + "]는 이미 등록된 이메일입니다.");
        }
    	List<User> savedUsers = userMapper.getUserByPhone(user.getPhone());
    	if (!savedUsers.isEmpty()) {
    	    throw new MallBusinessException("[" + user.getPhone() + "] 는 이미 등록된 전화번호입니다.");
        }

    	// 비밀번호 암호화
        String encodedPassword = DigestUtils.sha256Hex(user.getPassword());
    	user.setPassword(encodedPassword);

    	// 신규 사용자 정보 저장
        userMapper.insertUser(user);


    	// 신규 가입한 사용자에게 기본 적립포인트 지급
        user = userMapper.getUserById(user.getId());
        user.setPoint(defaultDepositPoint);
        userMapper.updateUser(user);
    }

}
