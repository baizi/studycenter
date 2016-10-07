package com.study.proxy.bean;

/**
 * Created by zhangshuwen
 * Date is 2016/9/14.
 * Time is 20:57
 */
public class RealPen implements Pen {

    @Override
    public void red() {
        System.out.println("this is a red pen");
    }

    @Override
    public void black() {
        System.out.println("this is a red pen");
    }
}
