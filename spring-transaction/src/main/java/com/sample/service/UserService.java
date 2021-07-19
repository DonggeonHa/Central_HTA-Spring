package com.sample.service;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sample.exception.MallBusinessException;
import com.sample.mapper.UserMapper;
import com.sample.vo.User;

@Service
public class UserService {
	
	@Value("${user.default.deposit.point}")
	private int defaultDepositPoint;
	
	@Autowired
	private UserMapper userMapper;
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
	/**
	 * 사용자정보를 전달받아서 회원가입 처리한다.
	 * @param user 신규 사용자 정보
	 */
	public void addNewUser(User user) {
		User savedUser = userMapper.getUserById(user.getId());
		if (savedUser != null) {
			throw new MallBusinessException("["+user.getId()+"]는 사용중인 아이디입니다.");
		}
		savedUser = userMapper.getUserByEmail(user.getEmail());
		if (savedUser != null) {
			throw new MallBusinessException("["+user.getEmail()+"]은 이미 등록된 이메일 입니다.");
		}
		List<User> savedUsers = userMapper.getUsersByPhone(user.getPhone());
		if (!savedUsers.isEmpty()) {
			throw new MallBusinessException("["+user.getPhone()+"]은 이미 등록된 전화번호 입니다.");
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
	
	/**
	 * 아이디를 전달받아서 사용자정보를 삭제한다.
	 * @param userId 사용자아이디
	 */
	public void removeUser(String userId) {
		userMapper.deleteUser(userId);
	}
	
	/**
	 * 아이디를 전달받아서 조회된 사용자 정보를 반환한다.
	 * @param userId 아이디
	 * @return 사용자정보
	 */
	public User getUserDetail(String userId) {
		return userMapper.getUserById(userId);
	}
	
}
