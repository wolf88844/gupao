package com.gupao.homework.singleton;

/**
 * @ClassName HungrySingleton
 * @Author LIUHANPENG
 * @Date 2019/11/11 0011 10:32
 **/
public class HungrySingleton {
    private HungrySingleton(){
        if(null!=hungrySingleton) {
            throw new RuntimeException("不允许创建多个实例");
        }
    }

    private static HungrySingleton hungrySingleton = new HungrySingleton();

    public static HungrySingleton getInstance(){
        return hungrySingleton;
    }
}
