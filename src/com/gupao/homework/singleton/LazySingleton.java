package com.gupao.homework.singleton;

/**
 * @ClassName LazySingleton
 * @Author LIUHANPENG
 * @Date 2019/11/11 0011 10:36
 **/
public class LazySingleton {
    private LazySingleton(){
        if(null!= lazySingleton) {
            throw new RuntimeException("不允许创建多个实例");
        }
    }
    private static LazySingleton lazySingleton = null;

    public static LazySingleton getInstance(){
        if(null==lazySingleton){
            synchronized (LazySingleton.class){
                if(null==lazySingleton){
                    lazySingleton = new LazySingleton();
                }
            }
        }
        return lazySingleton;
    }
}
