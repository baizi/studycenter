package com.study.zookeeper.task;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.BoundedExponentialBackoffRetry;

/**
 * docker工作.
 * User: zhangshuwen
 * Date: 2017/1/8
 * Time: 16:36
 */
public class DockerWorker implements NodeCacheListener {

    public final String connectString = "127.0.0.1:2181";

    private String ip;
    private String basePath;
    private CuratorFramework client;

    private NodeCache nodeCache;

    public DockerWorker(String ip, String basePath) throws Exception {
        this.ip = ip;
        this.basePath = basePath;

        initClient();
    }

    public void initClient() {

        RetryPolicy retryPolicy = new BoundedExponentialBackoffRetry(2000, 5000, 2);
        this.client = CuratorFrameworkFactory.newClient(connectString, retryPolicy);
    }

    public void registerIp() throws Exception {

        String path = basePath + ip;

        client.start();

        client.create().forPath(path);

//        client.close();
    }

    public void startLisnter() throws Exception {

        String path = basePath + ip;

        //设置节点的cache
        nodeCache = new NodeCache(client, path, false);
        nodeCache.getListenable().addListener(this);
        nodeCache.start();
    }

    public void nodeChanged() throws Exception {

        System.out.println("start run watch");
        System.out.println(nodeCache.getCurrentData().getPath());
        System.out.println(new String(nodeCache.getCurrentData().getData()));
    }

    public static void ipTest() throws Exception {

        String connectString = "127.0.0.1:2181";
        String path = "/tyrytryy";

        RetryPolicy retryPolicy = new BoundedExponentialBackoffRetry(2000, 5000, 2);
        CuratorFramework client = CuratorFrameworkFactory.newClient(connectString, retryPolicy);

        client.start();

        client.create().forPath(path);
        String data = new String(client.getData().forPath(path));
        System.out.println(data);
        client.close();
    }

    public static void main(String[] args) throws Exception {

        DockerWorker.ipTest();

//        String connectString = "127.0.0.1:2181";
//        String nodePath = "/jdorder17";
//        String base = nodePath + "/";
//        String prefix = "10.182.168.";
//
//        RetryPolicy retryPolicy = new BoundedExponentialBackoffRetry(2000, 5000, 2);
//        CuratorFramework client = CuratorFrameworkFactory.newClient(connectString, retryPolicy);
//
//        client.start();
//        client.create().forPath(nodePath);
//
//        for (int i = 0; i < 5; i++) {
//            DockerWorker worker = new DockerWorker(prefix + i, base);
//            worker.registerIp();
//            worker.startLisnter();
//        }
//
//        for (int i = 0; i < 5; i++) {
//            String data = "aixinjueluo" + i;
//            client.setData().forPath(base + prefix + i, data.getBytes());
//        }
//
//        for (int i = 0; i < 5; i++) {
//            String data = "liubei" + i;
//            client.setData().forPath(base + prefix + i, data.getBytes());
//        }
//
//        for (int i = 0; i < 5; i++) {
//            String data = "qianniuwei" + i;
//            client.setData().forPath(base + prefix + i, data.getBytes());
//        }
//        client.close();
//
//        Thread.sleep(100000);

    }
}
