package com.gupao.homework.proxy;

import java.lang.reflect.Method;

/**
 * @ClassName Meipo
 * @Author LIUHANPENG
 * @Date 2019/11/13 0013 13:58
 **/
public class Meipo implements MyInvocationHandler {
    private Object target;
    public Object getIntance(Object target){
        this.target = target;
        Class<?> clazz = target.getClass();
        return MyProxy.newProxyInstance(new MyClassLoader(),clazz.getInterfaces(),this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(this.target,args);
    }
}
