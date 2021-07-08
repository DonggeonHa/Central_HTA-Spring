package com.sample.di.demo5;

import org.springframework.stereotype.Repository;

@Repository("demo5.UserPointHistoryDao")
public class UserPointHistoryDao {
    public void getPointHistory(String userId) {
        System.out.println("["+ userId + "] 사용자의 포인트 변경이력을 조회합니다.");
    }
}
