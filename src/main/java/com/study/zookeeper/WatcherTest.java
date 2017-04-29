package com.study.zookeeper;

import org.apache.zookeeper.*;

import java.util.Iterator;
import java.util.List;

/**
 * describe.
 * User: zhangshuwen
 * Date: 2017/1/7
 * Time: 15:49
 */
public class WatcherTest {

    public static void main(String[] args) throws Exception {

        // Watcher实例
        Watcher wh = new Watcher() {

            public void process(WatchedEvent event) {

                if("/root".equals(event.getPath())) {
                    try {
                        Thread.sleep(100000);
                    } catch (Exception e) {

                    }
                }
                System.out.println("回调watcher实例： 路径" + event.getPath() + " 类型："
                        + event.getType() + " 状态：" + event.getState());
            }
        };

        ZooKeeper zk = new ZooKeeper("127.0.0.1:2181", 500000, wh);

        System.out.println("---------------------");

        // 创建一个节点root，数据是mydata,不进行ACL权限控制，节点为永久性的(即客户端shutdown了也不会消失)

        zk.exists("/root", true);
        zk.create("/root", "mydata".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);

        System.out.println("---------------------");

        // 在root下面创建一个childone znode,数据为childone,不进行ACL权限控制，节点为永久性的

        zk.exists("/root/childone", true);
//        System.out.println("6666666666666666");
        zk.create("/root/childone", "childone".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);

        System.out.println("---------------------");

        // 删除/root/childone这个节点，第二个参数为版本，－1的话直接删除，无视版本
        zk.exists("/root/childone", true);
        zk.delete("/root/childone", -1);
        System.out.println("---------------------");
        zk.exists("/root", true);
        zk.delete("/root", -1);
        System.out.println("---------------------");

        try {
            Thread.sleep(1000000);
        } catch (Exception e) {

        }

        // 关闭session
        zk.close();
    }
}
