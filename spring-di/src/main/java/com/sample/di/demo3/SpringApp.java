package com.sample.di.demo3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApp {

    public static void main(String[] args) {
        ApplicationContext container = new ClassPathXmlApplicationContext("context-di-demo3.xml");

        EventService eventService = container.getBean("eventService", EventService.class);

        eventService.noticeEvent("여름 휴가 대비하세요", "집 밖은 위험하니까 집콕할 수 있는 아이템을 받아가세요");

        SystemAlertService systemAlertService = container.getBean("systemAlertService", SystemAlertService.class);

        systemAlertService.alert("404오류", "페이지를 찾을 수 없습니다", "인텔리제이");

    }
}
