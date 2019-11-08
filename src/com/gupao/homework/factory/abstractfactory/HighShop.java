package com.gupao.homework.factory.abstractfactory;

import com.gupao.homework.factory.Banana;
import com.gupao.homework.factory.IFruit;

public class HighShop implements IShop {
    @Override
    public IFruit saleFruit() {
        return new Banana();
    }

    @Override
    public IMeat saleMeat() {
        return new CowMeat();
    }
}
