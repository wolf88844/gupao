package com.gupao.homework.factory.factory;

import com.gupao.homework.factory.Apple;
import com.gupao.homework.factory.IFruit;

public class AppleFactory implements IFactory {
    @Override
    public IFruit create() {
        return new Apple();
    }
}
