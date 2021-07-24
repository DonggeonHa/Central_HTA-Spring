package com.sample.vo;

import java.sql.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter						// toString메소드 재정의
@ToString					// 기본생성자 메소드 추가, 기본생성자 외의 생성자가 정의되어 있는 경우 기본생성자 추가할 때 사용, 
@NoArgsConstructor			// 기본생성자 없으면 mybatis에서 문제 생김
public class User {
	String id;
	String name;
	String password;
	String email;
	String phone;
	String status;
	Date createdDate;
	int point;
	
	
	/*
	 * @Builder
	 * 생성자를 하나만 생성할 수 있도록 해준다	 
	 * User.builder().id("hong").password("zxcv1234").build()=> 하면 아이디와 비밀번호만 갖는 User를 생성해준다.
	 */
	@Builder 
	public User(String id, String name, String password, String email, String phone) {
		super(); 
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.phone = phone;
	}
	
}
