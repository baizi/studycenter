package com.study.redis;

import com.google.common.collect.Lists;
import redis.clients.util.Hashing;
import redis.clients.util.SafeEncoder;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 根据key看分到了哪个shard上
 * 
 * @author wang_hui
 *
 */
public class GetRedisKeyShard {

    private TreeMap<Long, Integer> nodes;
    private final Hashing algo = Hashing.MURMUR_HASH;
    private Pattern tagPattern = Pattern.compile("\\{(.+?)\\}");

    private void initRedisShard() {
        nodes = new TreeMap<Long, Integer>();
        List<Integer> shards = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        for (int i = 0; i != shards.size(); ++i) {
            final Integer shardInfo = shards.get(i);
            for (int n = 0; n < 160; n++) {
                nodes.put(this.algo.hash("SHARD-" + i + "-NODE-" + n), shardInfo);
            }
        }
    }

    public Integer getShardInfo(byte[] key) {
        System.out.println(algo.hash(key));
        SortedMap<Long, Integer> tail = nodes.tailMap(algo.hash(key));
        if (tail.isEmpty()) {
            return nodes.get(nodes.firstKey());
        }
        return tail.get(tail.firstKey());
    }

    public Integer getShardInfo(String key) {
        return getShardInfo(SafeEncoder.encode(getKeyTag(key)));
    }

    public String getKeyTag(String key) {
        if (tagPattern != null) {
            Matcher m = tagPattern.matcher(key);
            if (m.find())
                return m.group(1);
        }
        return key;
    }

    public static void main(String[] args) {
        GetRedisKeyShard gs = new GetRedisKeyShard();
        gs.initRedisShard();
        System.out.println(gs.getShardInfo("visitor:ne_job@163.com:subs2"));
        // System.out.println(gs.getShardInfo(RedisKeyUtil.getKey(Key.MONTHLY_GOLD_OUT_STAT,
        // "cxd_03@163.com")));
    }
}
