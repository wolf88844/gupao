package com.gupao.homework.singleton;

/**
 * @ClassName InnerClassSingleton
 * @Author LIUHANPENG
 * @Date 2019/11/11 0011 10:51
 **/
public class InnerClassSingleton {
    private InnerClassSingleton(){}

    public static InnerClassSingleton getInstance(){
        return InnerClassHandler.innerClassSingleton;
    }
    private static class InnerClassHandler{
        private static final InnerClassSingleton innerClassSingleton = new InnerClassSingleton();
    }
}
