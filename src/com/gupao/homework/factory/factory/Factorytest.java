package com.gupao.homework.factory.factory;

import com.gupao.homework.factory.IFruit;

public  class Factorytest {
    public static void main(String[] args) {
        IFactory appleFactory = new AppleFactory();
        IFruit apple = appleFactory.create();
        apple.sale();
    }
}
