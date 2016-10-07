package com.study.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by zhangshuwen
 * Date is 2016/8/29.
 * Time is 20:14
 */
public class ProxyHandler implements InvocationHandler
{
    private Object proxied;

    public ProxyHandler( Object proxied )
    {
        this.proxied = proxied;
    }

    public Object invoke( Object proxy, Method method, Object[] args ) throws Throwable
    {
        //在转调具体目标对象之前，可以执行一些功能处理

        System.out.println("will proxy " + method.getName());
        //转调具体目标对象的方法
        return method.invoke( proxied, args);

        //在转调具体目标对象之后，可以执行一些功能处理
    }
}
