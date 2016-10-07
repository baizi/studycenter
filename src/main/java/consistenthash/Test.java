package consistenthash;

import com.google.common.collect.Maps;
import com.study.util.FileUtil;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhangshuwen
 * Date is 2016/10/2.
 * Time is 21:38
 */
public class Test {

    private final static String path = "G:\\vip.txt";

    public static void main(String[] args) {

        ConsistentHashRouter router = RouterFactory.create();

        Test.Counter counter = new Test().new Counter();

        FileUtil fileUtil = FileUtil.getInstance();
        List<String> results = fileUtil.read(path);

        for (String line : results) {
            PhysicalNode physicalNode = router.getNode(line);
            counter.count(physicalNode.getPort());
            System.out.println(line + " " + physicalNode.getPort());
        }

        counter.scan();
    }

    public class Counter {

        private Map<Integer,AtomicInteger> counter = Maps.newHashMap();

        public void count(Integer key) {

            if(counter.get(key) == null) {
                counter.put(key,new AtomicInteger(1));
            } else {
                counter.get(key).getAndIncrement();
            }
        }

        public AtomicInteger get(Integer key) {

            return counter.get(key);
        }

        public void scan() {

            Set<Integer> keys = counter.keySet();
            for(Integer key : keys) {
                System.out.println(key + "  " + counter.get(key));
            }
        }
    }
}
