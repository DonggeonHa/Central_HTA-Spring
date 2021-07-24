package com.sample.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
public class CartItem {

    private int no;
    private String userId;
    private int productNo;
    private int amount;
    private Date createdDate;
}
