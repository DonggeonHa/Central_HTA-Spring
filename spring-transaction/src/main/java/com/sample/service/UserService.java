package com.sample.service;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.exception.MallBusinessException;
import com.sample.mapper.UserMapper;
import com.sample.vo.User;

@Service
@Transactional
public class UserService {

	@Value("${user.default.deposit.point}")
	private int defaultDepositPoint;
	
	@Autowired
	private UserMapper userMapper;
	
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	

	/**
	 * 사용자정보를 전달받아서 회원가입 처리한다
	 * @param user
	 */
	public void addNewUser(User user) {
		User savedUser = userMapper.getUserById(user.getId());
		if(savedUser!=null) {
			throw new MallBusinessException("["+user.getId()+"]는 사용중인 아이디입니다.");
		}
		savedUser = userMapper.getUserByEmail(user.getEmail());
		if(savedUser!=null) {
			throw new MallBusinessException("["+user.getEmail()+"]는 사용중인 이메일입니다.");
		}
		List<User> savedUsers = userMapper.getUserByPhone(user.getPhone());
		if(!savedUsers.isEmpty()) {
			throw new MallBusinessException("["+user.getPhone()+"]는 사용중인 번호입니다.");
		}
		
		//비밀번호 암호화
		String encodedPassword = DigestUtils.sha256Hex(user.getPassword());
		user.setPassword(encodedPassword);
		
		
		//신규 사용자 정보 저장
		userMapper.insertUser(user);
		
		int i=10;
		if(i==10) {
			throw new RuntimeException("강제 예외 발생");
		}	//예외발생으로 인해 가입은 되었으나 아래의 포인트 지급이 실행되지 않음-> 트랜잭션 처리가 되어있을 경우 롤백됨
		
		//신규 가입한 사용자에게 기본 적립포인트 지급
		user = userMapper.getUserById(user.getId());  // status등 db에 기본값으로 등록되어있는 컬럼이 null상태이다.  
													  // db에서 객체를 받아온 뒤 Update를 통해 해결한다
		user.setPoint(defaultDepositPoint);	//100은 바뀔 수 있는 포인트	=> properties 파일에 따로 빼서 관리 
		userMapper.updateUser(user);
	}
	
	/**
	 * 유저 id 입력받아서 유저 삭제
	 * 아주 단순한 메소드라도 표현 계층 - service계층 - persistence계층 간 위계를 지켜서 메소드 가져올것
	 * ex) 바로 userMapper의 deleteUser()를 사용하지 말고, 서비스 계층의 removeUser()를 통해 
	 * 		메소드를 불러온다.
	 * @param userId
	 */
	public void removeUser(String userId) {
		userMapper.deleteUser(userId);
	}
	
	/* 
	 * mapper인터페이스에서 정의한 메소드는 sql을 잘 작성했느냐 차원의 문제이므로 이런 메소드는 단위테스트를 따로 진행하지 않음.
	 */
	public User getUserDetail(String userId) {
		return userMapper.getUserById(userId);
	}
}
