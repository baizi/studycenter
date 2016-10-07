package com.study.thread.masterAndworker;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by zhangshuwen
 * Date is 2016/9/16.
 * Time is 23:18
 */
public class Master {
    //任务队列
    protected Queue<Object> workQueue = new ConcurrentLinkedQueue<Object>();
    //worker进程队列
    protected Map<String, Thread> threadMap = new HashMap<String, Thread>();
//    //结果集
//    protected Map<String, Object> resultMap = new HashMap<String, Object>();

    protected ConcurrentHashMap<String,Object> resultMap = new ConcurrentHashMap<String,Object>();

    //是否所有的子任务都结束

    public boolean isComplete() {
        for (Map.Entry<String, Thread> entry : threadMap.entrySet()) {
            if (entry.getValue().getState() != Thread.State.TERMINATED) {
                return false;
            }
        }
        return true;
    }

    public Master(Worker worker, int countWorker) {
        worker.setResultMap(resultMap);
        worker.setWorkQueue(workQueue);
        for (int i = 0; i < countWorker; i++) {
            threadMap.put(Integer.toString(i), new Thread(worker, Integer.toString(i)));
        }
    }

    //提交任务

    public void submit(Object obj) {
        workQueue.add(obj);
        //System.out.println(obj.toString());
    }


    //返回子任务结果集
    public ConcurrentHashMap<String,Object> getResultMap() {
        return resultMap;
    }

    //开始运行所有worker进程，并进行处理

    public void execute() {
        for (Map.Entry<String, Thread> entry : threadMap.entrySet()) {
            entry.getValue().start();
        }
    }

}
