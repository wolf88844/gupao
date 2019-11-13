package com.gupao.homework.thread;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName test
 * @Author LIUHANPENG
 * @Date 2019/11/13 0013 10:09
 **/
public class test {
    static final Object object = new Object();
    static Integer count=0;
    static int  count1=0;
    static AtomicInteger atomicIntegert= new AtomicInteger(0);
    static String str = new String();
    ReentrantLock lock = new ReentrantLock();
    public static void incr(){
        synchronized (test.class) {
            try {
                Thread.sleep(1);
                count++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {

            }

        }
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        for(int i=0;i<1000;i++){
            new Thread(()->test.incr()).start();
        }
        Thread.sleep(1000);
        System.out.println("result:"+count);
    }
}
