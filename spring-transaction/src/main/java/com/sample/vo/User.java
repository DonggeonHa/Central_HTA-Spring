package com.sample.vo;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter				// gett메소드 추가
@Setter				// setter메소드 추가
@ToString			// toString메소드 재정의
@NoArgsConstructor	// 기본생성자 메소드 추가, 기본생성자 외의 생성자가 정의되어 있는 경우 기본 생성자를 추가할 때 사용
public class User {

	private String id;
	private String name;
	private String password;
	private String email;
	private String phone;
	private String status;
	private int point;
	private Date createdDate;
	
	@Builder // User 클래스의 초기화를 담당하는 빌더패턴의 빌더클래스를 추가한다.
	public User(String id, String name, String password, String email, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.phone = phone;
	}
	
	
}
