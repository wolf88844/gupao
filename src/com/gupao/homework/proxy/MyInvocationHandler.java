package com.gupao.homework.proxy;

import java.lang.reflect.Method;

/**
 * @ClassName MyInvocationHandler
 * @Author LIUHANPENG
 * @Date 2019/11/13 0013 13:06
 **/
public interface MyInvocationHandler {
    public Object invoke(Object proxy, Method method,Object[] args) throws Throwable;
}
