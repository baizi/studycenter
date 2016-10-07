package com.study.thread.masterAndworker;

import java.util.Map;
import java.util.Set;

/**
 * Created by zhangshuwen
 * Date is 2016/9/16.
 * Time is 23:20
 */
public class Client {

    public static void main(String[] args) {

        Master m = new Master(new PlusWorker(), 5);//启动五个线程处理
        for (int i = 1; i <= 100; i++) {
            m.submit(i);
        }
        m.execute();

        int result = 0;
        Map<String, Object> resultMap = m.getResultMap();
        while (resultMap.size() > 0 || !m.isComplete()) {

            Set<String> keys = resultMap.keySet();

            for(String key : keys) {

                Integer value = null;
                if (key != null) {
                    value = (Integer) resultMap.get(key);
                }
                if (value != null) {
                    result += value;//并行计算结果集
                }

                if (key != null) {
                    resultMap.remove(key);//将计算完成的结果移除
                }
            }
        }

        System.out.println(result);
    }
}