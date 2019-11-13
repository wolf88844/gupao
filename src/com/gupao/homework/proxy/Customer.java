package com.gupao.homework.proxy;

/**
 * @ClassName Customer
 * @Author LIUHANPENG
 * @Date 2019/11/13 0013 14:01
 **/
public class Customer implements Person {
    @Override
    public void findLove() {
        System.out.println("执行方法");
    }
}
