package com.gupao.homework.factory.factory;

import com.gupao.homework.factory.Banana;
import com.gupao.homework.factory.IFruit;

public class BananaFactory implements IFactory {
    @Override
    public IFruit create() {
        return new Banana();
    }
}
