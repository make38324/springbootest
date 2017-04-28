package com.czm;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

/**
 * Created by mac on 17/4/28.
 */
public class TimerTest {
    public static void main(String[] args){
        ScheduledExecutorService service= Executors.newScheduledThreadPool(5);
        long oneDay = 24 * 60 * 60 * 1000;
        long initDelay  = getTimeMillis("20:00:00") - System.currentTimeMillis();//每天8点执行
        initDelay = initDelay > 0 ? initDelay : oneDay + initDelay;
        Runnable run = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello"+Thread.currentThread().getName());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print("h2"+Thread.currentThread().getName());
            }
        };
        service.scheduleAtFixedRate(run,initDelay,1,TimeUnit.DAYS);
    }

    /**
     * 获取指定时间对应的毫秒数
     * @param time "HH:mm:ss"
     * @return
     */
    private static long getTimeMillis(String time) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
            DateFormat dayFormat = new SimpleDateFormat("yy-MM-dd");
            Date curDate = dateFormat.parse(dayFormat.format(new Date()) + " " + time);
            return curDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
