package com.gupao.homework.factory.simplefactory;

import com.gupao.homework.factory.IFruit;

public class SimpleFactory {

    public IFruit create(Class<? extends IFruit> clazz){
        if(null!=clazz){
            try {
                return clazz.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;

    }

}
