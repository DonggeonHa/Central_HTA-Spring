package com.sample.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Todo {

	private int no;
	private String category;
	private String writer;
	private String title;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dueDate;
	private String content;
	private String status;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date completedDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date updatedDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date deletedDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createdDate;
}
/*
 *   @DateTimeFormat(pattern = "yyyy-MM-dd")
 *      - 입력필드에서 입력한 문자열 형식의 날짜를 Date타입 객체와 매핑시키는 어노테이션이다.
 *      - pattern 속성에서는 날짜 형식을 지정한다.
 *
 *  @JsonFormat(pattern = "yyyy-MM-dd")
 *      - @JsonFormat이 지정된 Date 타입의 값이 json 형식이 변환될 때 지정된 패턴 형식의 텍스트로 변한다.
 */