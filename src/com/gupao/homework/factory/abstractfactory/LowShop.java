package com.gupao.homework.factory.abstractfactory;

import com.gupao.homework.factory.Apple;
import com.gupao.homework.factory.IFruit;

public class LowShop implements IShop {
    @Override
    public IFruit saleFruit() {
        return new Apple();
    }

    @Override
    public IMeat saleMeat() {
        return new PigMeat();
    }
}
