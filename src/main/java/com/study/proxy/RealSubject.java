package com.study.proxy;

import com.study.proxy.bean.Pen;

/**
 * Created by zhangshuwen
 * Date is 2016/8/29.
 * Time is 20:14
 */
public class RealSubject implements Subject
{

    private Pen pen;

    public void setPen(Pen pen) {
        this.pen = pen;
    }

    @Override
    public void doSomething()
    {
        System.out.println( "call doSomething()" );
    }

    @Override
    public void doOtherThing() {
        System.out.println( "call otherThing()" );
    }

    @Override
    public void transantionA() {

//        transantionB();
//        transantionC();

        pen.black();
        pen.red();
    }

    @Override
    public void transantionB() {
        System.out.println( "call transantionB()" );
    }

    @Override
    public void transantionC(){
        System.out.println( "call transantionC()" );
    }

}
