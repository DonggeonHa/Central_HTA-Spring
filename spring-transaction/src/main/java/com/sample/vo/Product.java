package com.sample.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class Product {
	private int no;
	private String name;
	private String maker;
	private String category;
	private int price;
	private int discountPrice;
	private int stock;
	private String soldOut;
	private int reviewCnt;
	private Date createdDate;
	
}
