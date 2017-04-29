package com.study.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.BoundedExponentialBackoffRetry;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Curator的学习.
 * User: zhangshuwen
 * Date: 2016/12/26
 * Time: 19:35
 */
public class CuratorStudy {

    public static String connectString = "127.0.0.1:2181";
    private int timeout = 15000;

    final static String path = "/jingdong/zhangshuwen";

    public void zookeeperOp(Operator op) {
        RetryPolicy retryPolicy = new BoundedExponentialBackoffRetry(2000, 5000, 2);
        CuratorFramework cf = CuratorFrameworkFactory.newClient(connectString, retryPolicy);

        try {

//            cf.getConnectionStateListenable().addListener(new ConnectionStateListener() {
//                public void stateChanged(CuratorFramework client, ConnectionState newState) {
//                    System.out.println("stateChanged " + newState.name());
//                }
//            });

//            cf.getUnhandledErrorListenable().addListener(new UnhandledErrorListener() {
//                public void unhandledError(String message, Throwable e) {
//                    System.out.println("unhandledError");
//                    e.printStackTrace();
//                }
//            });
//
//            cf.getCuratorListenable().addListener(new CuratorListener() {
//                public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception {
//                    System.out.println("============================event start================================");
//                    System.out.println(event != null ? event.getName() : "name is null");
////                    System.out.println(event != null ? new String(event.getData()) : "data is null");
//                    System.out.println(event != null ? event.getPath() : "data is path");
//                    System.out.println(event != null ? event.getStat() : "data is stat");
//                    System.out.println(event != null ? event.getType() : "data is type");
//                    System.out.println("============================event end================================");
//                }
//            });

            cf.start();

//            watch3(cf);

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

    public static void watch(CuratorFramework client) throws Exception {
        byte[] data = client.getData().usingWatcher(new Watcher() {
            public void process(WatchedEvent event) {
                System.out.println(path + " : " + event);
            }
        }).forPath(path);

        System.out.println("The data become " + new String(data));
    }

    public static void watch2(CuratorFramework client) throws Exception {

        ExecutorService pool = Executors.newCachedThreadPool();

        client.getCuratorListenable().addListener(new CuratorListener() {
            public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception {
                System.out.println("监听事件 ： " + event);
            }
        }, pool);

        System.out.println("1 " + new String(client.getData().forPath(path)));
        System.out.println("2 " + new String(client.getData().forPath(path)));
        System.out.println("3 " + new String(client.getData().forPath(path)));
        System.out.println("4 " + new String(client.getData().forPath(path)));

        Thread.sleep(10000000);
    }

    //1.path Cache  连接  路径  是否获取数据
    //能监听所有的字节点 且是无限监听的模式 但是 指定目录下节点的子节点不再监听
    private static void watch3(CuratorFramework client) throws Exception{

        PathChildrenCache childrenCache = new PathChildrenCache(client, path, true);
        PathChildrenCacheListener childrenCacheListener = new PathChildrenCacheListener() {

            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                System.out.println("开始进行事件分析:-----");
                ChildData data = event.getData();
                switch (event.getType()) {
                    case CHILD_ADDED:
                        System.out.println("CHILD_ADDED : "+ data.getPath() +"  数据:"+ new String(data.getData()));
                        break;
                    case CHILD_REMOVED:
                        System.out.println("CHILD_REMOVED : "+ data.getPath() +"  数据:"+ new String(data.getData()));
                        break;
                    case CHILD_UPDATED:
                        System.out.println("CHILD_UPDATED : "+ data.getPath() +"  数据:"+ new String(data.getData()));
                        break;
                    default:
                        break;
                }
            }
        };
        childrenCache.getListenable().addListener(childrenCacheListener);
//        System.out.println("Register zk watcher successfully!");
        childrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
    }

    public static void watch4(CuratorFramework client) throws Exception{

        //设置节点的cache
        final NodeCache nodeCache = new NodeCache(client, path, false);
        nodeCache.getListenable().addListener(new NodeCacheListener() {

            public void nodeChanged() throws Exception {
                System.out.println("the test node is change and result is :");
                System.out.println("path : "+nodeCache.getCurrentData().getPath());
                System.out.println("data : "+new String(nodeCache.getCurrentData().getData()));
                System.out.println("stat : "+nodeCache.getCurrentData().getStat());
            }
        });
        nodeCache.start();
    }

    public static void main(String[] args) {

        CuratorStudy study = new CuratorStudy();
        study.zookeeperOp(new Operator() {
            public void _do(CuratorFramework cf) {

                try {

                    /**
                     * get state
                     */
//                    System.out.println(cf.getState().toString());

                    /**
                     * create
                     */
//                    String result = cf.create().forPath(path + "/liyanan2","张树文".getBytes());
//                    System.out.println(result);

                    /**
                     * get
                     */
//                    byte[] data = cf.getData().forPath(path);
//                    System.out.println(new String(data));

                    /**
                     * update
                     */
//                    cf.setData().forPath(path, "susan33".getBytes());

                    /**
                     * check exit , return null if no path
                     */
//                    Stat stat = cf.checkExists().forPath(path);
//                    System.out.println(stat);

                    /**
                     * select list
                     */
//                    String result = cf.create().forPath(path,"songbo8".getBytes());
//                    System.out.println(result);
//
//                    List<String> children = cf.getChildren().forPath("/jingdong/zhangshuwen");
//                    for(String child : children) {
//                        System.out.println(child);
//                    }

                    /**
                     * delete
                     */
//                    cf.delete().forPath(path);
//                    Stat stat = cf.checkExists().forPath(path);
//                    System.out.println(stat);


                    /**
                     * 好深奥，不懂.
                     */
//                    LeaderLatch leaderLatch = new LeaderLatch(cf,path,"BF4251");
//
//                    leaderLatch.addListener(new LeaderLatchListener() {
//                        public void isLeader() {
//                            System.out.println("i am leader");
//                        }
//
//                        public void notLeader() {
//                            System.out.println("i am not leader");
//                        }
//                    });
//
//                    leaderLatch.start();
//
//                    /**
//                     * leaderselector
//                     */
//                    LeaderSelectorListener listener = new LeaderSelectorListener() {
//                        public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
//
//                        }
//
//                        public void stateChanged(CuratorFramework client, ConnectionState newState) {
//
//                        }
//                    };
//
//                    LeaderSelector selector = new LeaderSelector(cf,path,listener);


                    List<String> children = cf.getChildren().forPath(path);
                    for(String child : children) {

                        cf.delete().forPath(path + "/" + child);
                    }

                    cf.create().forPath(path + "/a1","aaa".getBytes());
                    cf.create().forPath(path + "/a2","bbb".getBytes());
                    cf.create().forPath(path + "/a3","ccc".getBytes());
                    cf.create().forPath(path + "/a4","ddd".getBytes());

                    cf.delete().forPath(path + "/a1");
                    cf.delete().forPath(path + "/a2");
                    cf.delete().forPath(path + "/a3");
                    cf.delete().forPath(path + "/a4");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
