package com.study.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * describe.
 * User: zhangshuwen
 * Date: 2016/11/25
 * Time: 10:07
 */
public class Master implements Watcher {

    private ZooKeeper zk;
    private String hostPort;

    public Master(String hostPort) {
        this.hostPort = hostPort;
    }

    public void startZK() throws IOException {
        zk = new ZooKeeper(hostPort,15000,this);
    }

    public void process(WatchedEvent watchedEvent) {
        System.out.println(watchedEvent);
    }

    public static void main(String[] args) throws Exception {

        try {

            Master m = new Master("127.0.0.1:2181");
            m.startZK();

            Thread.sleep(60000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
