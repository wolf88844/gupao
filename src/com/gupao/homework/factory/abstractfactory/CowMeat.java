package com.gupao.homework.factory.abstractfactory;

public class CowMeat implements IMeat {
    @Override
    public void sale() {
        System.out.println("卖牛肉");
    }
}
