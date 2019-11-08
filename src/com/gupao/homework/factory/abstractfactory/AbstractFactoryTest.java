package com.gupao.homework.factory.abstractfactory;

import com.gupao.homework.factory.IFruit;

public class AbstractFactoryTest {
    public static void main(String[] args){
        IShop shop1 = new LowShop();
        IFruit fruit1 = shop1.saleFruit();
        fruit1.sale();
        IMeat iMeat1 = shop1.saleMeat();
        iMeat1.sale();

        IShop shop2 = new HighShop();
        IFruit fruit2 = shop2.saleFruit();
        fruit2.sale();
        IMeat iMeat2 = shop2.saleMeat();
        iMeat2.sale();
    }
}
