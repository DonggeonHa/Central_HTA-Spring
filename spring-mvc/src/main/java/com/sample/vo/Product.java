package com.sample.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
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

    public Product() {}

}
