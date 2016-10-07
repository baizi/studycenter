package com.study.thread.masterAndworker;

/**
 * Created by zhangshuwen
 * Date is 2016/9/16.
 * Time is 23:20
 */
public class PlusWorker extends Worker {

    @Override
    public Object handle(Object input) {
        Integer i = (Integer) input;
        return i;
    }

}
