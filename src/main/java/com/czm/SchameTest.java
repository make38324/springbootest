package com.czm;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by mac on 17/4/28.
 */
@Component
public class SchameTest {
//    @Scheduled(cron="0/1 * * * * ?") //每秒执行一次
    public void printhello(){
        System.out.println("hello");
    }
}
