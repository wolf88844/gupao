package com.gupao.homework.singleton;

/**
 * @ClassName EnumSingleton
 * @Author LIUHANPENG
 * @Date 2019/11/11 0011 11:01
 **/
public enum  EnumSingleton {
    INSTANCEOF;

    public static EnumSingleton getInstance(){
        return INSTANCEOF;
    }
}
