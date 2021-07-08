package com.sample.di.demo2;

import com.sample.di.demo1.CustomerDao;
import com.sample.di.demo1.OrderDao;

public class CustomerService {

    private CustomerDao customerDao;
    private OrderDao orderDao;

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void getMyInfo() {
        // 고객의 상세정보 조회      <-- CustomerDao의 getCustomerById()
        System.out.println("고객 상세정보 요청을 처리합니다.");
        customerDao.getCustomerById();
    }

    public void getMyOrderList() {
        // 고객의 주문내역 조회      <-- OrderDao의 getOrderListByUserId()
        System.out.println("고객 주문내역 조회를 처리합니다.");
        orderDao.getOrderListByUserId();
    }
}
