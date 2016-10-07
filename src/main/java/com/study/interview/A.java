package com.study.interview;

/**
 * Created by zhangshuwen
 * Date is 2016/9/19.
 * Time is 22:57
 */
public class A {

    public static void main(String[] args) {

        String s1 = "helloworld";
        String s2 = new String("helloworld");
        System.out.println(s1 == s2);
        String s3 = "hello";
        String s4 = "world";
        String s5 = s3 + s4;
        System.out.println(s1 == s5);
        String s7 = "hello" + "world";
        System.out.println(s1 == s7);

        Integer a = 3;
        Integer b = 3;
        System.out.println(a == b);
        Integer c = new Integer(3);
        Integer d = new Integer(3);
        System.out.println(c == d);
        Integer e = c + a;
        Integer f = 6;
        System.out.println(e == f);
    }
}
