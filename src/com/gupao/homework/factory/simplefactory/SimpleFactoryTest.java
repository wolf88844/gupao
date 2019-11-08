package com.gupao.homework.factory.simplefactory;

import com.gupao.homework.factory.Apple;
import com.gupao.homework.factory.Banana;
import com.gupao.homework.factory.IFruit;

public class SimpleFactoryTest {

    public static void main(String[] args) {
        SimpleFactory simpleFactory = new SimpleFactory();
        IFruit apple = simpleFactory.create(Apple.class);
        apple.sale();
        IFruit banana = simpleFactory.create(Banana.class);
        banana.sale();
    }
}
