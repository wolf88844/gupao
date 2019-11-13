package com.gupao.homework.proxy;

/**
 * @ClassName Test
 * @Author LIUHANPENG
 * @Date 2019/11/13 0013 14:01
 **/
public class Test {
    public static void main(String[] args){
        Person intance = (Person)new Meipo().getIntance(new Customer());
        System.out.println(intance.getClass());
        intance.findLove();
    }
}
