package consistenthash;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by zhangshuwen
 * Date is 2016/10/2.
 * Time is 21:37
 */
public class RouterFactory {

    private final static int virtualCount = 160;

    public static ConsistentHashRouter create() {

        Collection<PhysicalNode> collections = new ArrayList<PhysicalNode>();

        collections.add(new PhysicalNode("c.m.163.com", "220.181.98.57", 16322));
        collections.add(new PhysicalNode("c.m.com", "220.181.98.55", 1046));
        collections.add(new PhysicalNode("c.163.com", "220.181.98.54", 163));

        collections.add(new PhysicalNode("t.c.m.163.com", "220.181.98.44", 132));

        return new ConsistentHashRouter(collections, virtualCount);
    }
}
