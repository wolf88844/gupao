package com.gupao.homework.singleton;

/**
 * @ClassName ThreadLoaclSingleton
 * @Author LIUHANPENG
 * @Date 2019/11/11 0011 11:07
 **/
public class ThreadLoaclSingleton  {
    private static final ThreadLocal<ThreadLoaclSingleton> threadLocal = new ThreadLocal(){
        @Override
        protected Object initialValue() {
            return new ThreadLoaclSingleton();
        }
    };
    private ThreadLoaclSingleton(){}

    public static  ThreadLoaclSingleton getInstance(){
        return threadLocal.get();
    }
}
