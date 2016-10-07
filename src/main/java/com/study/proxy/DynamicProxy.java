package com.study.proxy;

/**
 * Created by zhangshuwen
 * Date is 2016/8/29.
 * Time is 20:16
 */
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.study.proxy.bean.Pen;
import com.study.proxy.bean.RealPen;
import sun.misc.ProxyGenerator;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class DynamicProxy
{
    public static void main( String args[] )
    {
        RealPen pen = new RealPen();
        Pen proxypen = (Pen)Proxy.newProxyInstance(Pen.class.getClassLoader(),
                new Class[]{Pen.class},
                new ProxyHandler(pen));

        RealSubject real = new RealSubject();
        real.setPen(proxypen);

        Subject proxySubject = (Subject)Proxy.newProxyInstance(Subject.class.getClassLoader(),
                new Class[]{Subject.class},
                new ProxyHandler(real));

        proxySubject.transantionA();


//        PlatformTransactionManager
//        TransactionProxyFactoryBean

//        System.out.println("11111");
//        proxySubject.doSomething();
//
//        System.out.println("333333");
//        proxySubject.doOtherThing();

//        ExecutorService service = Executors.newSingleThreadExecutor();

        //write proxySubject class binary data to file
//        createProxyClassFile();
    }

    public static void createProxyClassFile()
    {
        String name = "ProxySubject";
        byte[] data = ProxyGenerator.generateProxyClass( name, new Class[] { Subject.class } );
        try
        {
            FileOutputStream out = new FileOutputStream( name + ".class" );
            out.write( data );
            out.close();
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }
}