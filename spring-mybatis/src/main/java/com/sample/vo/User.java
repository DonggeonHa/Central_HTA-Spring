package com.sample.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class User {

    private String id;
    private String name;
    private String password;
    private String email;
    private String phone;
    private String status;
    private int point;
    private Date createdDate;

    public User() {}

}
