package com.study.zookeeper;

import org.apache.curator.framework.CuratorFramework;

/**
 * describe.
 * User: zhangshuwen
 * Date: 2016/12/26
 * Time: 22:25
 */
public interface Operator {

    void _do(CuratorFramework cf);
}
