package com.czm;

import org.junit.Test;

/**
 * Created by mac on 17/4/17.
 */
public class ThreadTest {
    static Object obj;
    static {
        obj=new Object();
    }
    @Test
    public void testThread(){
    }
    public static class MyRunnable implements Runnable{
        private String name;
        public MyRunnable(String name){
            this.name=name;
        }
        @Override
        public void run() {
            synchronized (obj) {
//                if(name.equals("B2")){
//                    try {
//                        obj.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
                System.out.println(name);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name + "执行完毕");
//                obj.notifyAll();
            }
        }
    }
    public static void main(String[] args){
        //先执行thread1 再执行thread2;
        Thread thread1=new Thread(new MyRunnable("A1"));
        Thread thread2=new Thread(new MyRunnable("B2"));
        thread2.start();
        thread1.start();
    }
}
