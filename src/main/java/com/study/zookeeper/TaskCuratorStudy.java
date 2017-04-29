package com.study.zookeeper;

import org.apache.commons.lang.StringUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.BoundedExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;

import java.net.InetAddress;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Curator的学习.
 * User: zhangshuwen
 * Date: 2016/12/26
 * Time: 19:35
 */
public class TaskCuratorStudy {

    public static String connectString = "192.168.155.112:2181";
    final static String path = "/king/dbinfo";

    public void zookeeperOp(Operator op) {
        RetryPolicy retryPolicy = new BoundedExponentialBackoffRetry(2000, 5000, 2);
        CuratorFramework cf = CuratorFrameworkFactory.newClient(connectString, retryPolicy);

        try {

            cf.start();

            Thread.sleep(10000);
            op._do(cf);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cf.close();
            try {
                Thread.sleep(10000);
            } catch (Exception e) {

            }
            System.out.println("the state = " + cf.getState().toString());
        }
    }

    public static void main(String[] args) {

        TaskCuratorStudy study = new TaskCuratorStudy();
        study.zookeeperOp(new Operator() {
            public void _do(CuratorFramework cf) {

                try {

//                    cf.create().withMode(CreateMode.PERSISTENT).forPath("/king");
//                    cf.create().withMode(CreateMode.PERSISTENT).forPath(path);

                    /**
                     * update.
                     */
//                    cf.create().withMode(CreateMode.PERSISTENT).forPath(path + "/192.168.155.112","0,1,2".getBytes());

//                    Stat stat = cf.setData().forPath(path + "/192.168.155.112","0,1,2,3".getBytes());
//                    System.out.println(stat);
//
//                    byte [] data = cf.getData().forPath(path + "/192.168.155.112");
//
//                    System.out.println(new String(data));

//                    /**
//                     * update.
//                     */
//                    cf.create().withMode(CreateMode.PERSISTENT).forPath(path + "/" + getLocalIp(),"0,1,2".getBytes());
//
//                    Stat stat = cf.setData().forPath(path + "/" + getLocalIp(),"0,1,2,3".getBytes());
//                    System.out.println(stat);
//
//                    byte [] data = cf.getData().forPath(path + "/" + getLocalIp());
//
//                    System.out.println(new String(data));

                    /**
                     * 删除
                     */
                    String path = "/king";
                    List<String> children = cf.getChildren().forPath(path);
                    for (String child : children) {
//
//                        System.out.println(child);
//                        System.out.println(cf.getChildren().forPath(path + "/" + child).size());

                        String childPath = path + "/" + child;
                        if (cf.getChildren().forPath(childPath).size() == 0) {
                            cf.delete().forPath(childPath);
                            System.out.println("delete sucess" + childPath);
                        } else {
                            List<String> sonList = cf.getChildren().forPath(childPath);
                            for (String son : sonList) {
                                String sonPath = childPath + "/" + son;

                                if (cf.getChildren().forPath(sonPath).size() == 0) {
                                    cf.delete().forPath(sonPath);
                                    System.out.println("delete sucess" + sonPath);
                                }
                            }
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static String getLocalIp() {
        try {
            InetAddress addr = InetAddress.getLocalHost();
            return addr.getHostAddress();
        } catch (Exception e) {
            return StringUtils.EMPTY;
        }
    }

}
