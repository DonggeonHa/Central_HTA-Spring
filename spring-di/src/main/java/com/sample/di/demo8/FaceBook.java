package com.sample.di.demo8;

public class FaceBook implements Sns{

    @Override
    public void post(String title, String content) {
        System.out.println("페이스북에 포스팅하기");
        System.out.println("제목 : " + title);
        System.out.println("내용 : " + content);
    }
}
