package com.czm;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by mac on 17/4/17.
 * wait释放cpu资源 释放锁
 * sleep当前Thread释放cpu资源 但不释放锁
 */
public class ThreadTest {
    static final int max = 10;
    static int current = 0;
    static Object obj;
    static int joinnum=0;
    static int picknum=100;
    static {
        obj = new Object();
    }

    public static void main(String[] args) {
//        test1();
//          test2();//线程间通信 wait notify必须锁对象必须唯一 生产者消费者
//          test3();//join用法
          test4();
    }

    private static void test4() {
        for(int i=0;i<10;i++) {
            Thread thread= new Thread(new MyPickRunnable(),"售票线程"+i);
            thread.start();
        }

    }
    static ReentrantLock reentrantLock=new ReentrantLock();
    public static class MyPickRunnable implements Runnable{
        @Override
        public void run() {
            while (picknum>0){
                reentrantLock.lock();
                if(picknum>0) {
                    --picknum;
                    System.out.println(Thread.currentThread().getName() + ":" + picknum);
                }
                reentrantLock.unlock();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void test1() {
        //先执行thread1 再执行thread2;
        Thread thread1 = new Thread(new MyRunnable("A1"));
        Thread thread2 = new Thread(new MyRunnable("B2"));
        thread2.start();
        thread1.start();
    }
    //生产消费者
    public static void test2() {
        new Thread(new XiaofeiRunnable()).start();
        new Thread(new ShenCanRunnable()).start();
    }
    //join
    public static void test3(){
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;i++){
                    joinnum+=1;
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
        try {
//            thread.join();
            thread.join(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(joinnum+"");
    }
    public static class ShenCanRunnable implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (obj) {
                    while (current >= max) {
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    ++current;
                    System.out.println("生产线程:" + current);
                    obj.notify();
                }
//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }
    }

    public static class XiaofeiRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("消费线程");
            while (true) {
                synchronized (obj) {
                    while (current <= 0) {
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    --current;
                    System.out.println("消费线程:" + current);
                    obj.notify();
                }
//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }

        }
    }

    public static class MyRunnable implements Runnable {
        private String name;

        public MyRunnable(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            synchronized (obj) {
                if (name.equals("B2")) {//B2先进来wait了，A1notify后B2就不会再判断了
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(name);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name + "执行完毕");
                obj.notifyAll();
            }
        }
    }
}
