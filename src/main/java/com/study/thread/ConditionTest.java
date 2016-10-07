package com.study.thread;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

/**
 * Created by zhangshuwen
 * Date is 2016/9/5.
 * Time is 11:21
 */
public class ConditionTest {

    public static void main(String[] args) {

        List<String> list = Lists.newArrayList();
        Collections.synchronizedCollection(list);
    }
}
