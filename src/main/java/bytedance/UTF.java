package bytedance;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author kuangjunlin
 */
public class UTF {
    public boolean validUtf8 (int[] data) {
        int numberOfbytesToProcess = 0;
        int mask1 = 1 << 7;
        int mask2 = 1 << 6;

        for (int aData : data) {
            if (numberOfbytesToProcess == 0) {
                int mask = 1 << 7;
                while ((mask & aData) != 0) {
                    numberOfbytesToProcess += 1;
                    mask = mask >> 1;
                }
                if (numberOfbytesToProcess == 0) continue;
                if (numberOfbytesToProcess > 4 || numberOfbytesToProcess == 1) return false;
            } else {
                if (!((aData & mask1) != 0 && (mask2 & aData) == 0)) {
                    return false;
                }
            }
            numberOfbytesToProcess -= 1;
        }
        return numberOfbytesToProcess == 0;
    }
}
class AllOne {

    class Node implements Comparable<Node>{
        String key ;
        int value;
        @Override
        public int compareTo(Node o) {
            if(value-o.value == 0) {
                return key.compareTo(o.key) ;
            }
            return value-o.value;
        }
        Node(String key,int value){
            this.key = key ;
            this.value = value ;
        }
        @Override
        public String toString() {
            return "[key=" + key + ", value=" + value + "]";
        }

    }

    private Map<String,Node> map = new HashMap<>() ;

    private TreeMap<Node,Integer> treeMap = new TreeMap<>() ;


    public AllOne() {

    }

    public void inc(String key) {
        Node node = map.get(key) ;
        if(node == null) {
            node = new Node(key,1) ;
            map.put(key, node) ;
            treeMap.put(node,1) ;
        }else {
            treeMap.remove(node) ;
            node.value++ ;
            treeMap.put(node, 1) ;
        }
    }

    public void dec(String key) {
        Node node = map.get(key) ;
        if(node != null) {
            treeMap.remove(node) ;
            node.value-- ;
            if(node.value == 0) {
                map.remove(key) ;
            }else {
                treeMap.put(node, 1) ;
            }
        }
    }

    public String getMaxKey() {
        if(treeMap.isEmpty()) {
            return "" ;
        }
        return treeMap.lastEntry().getKey().key ;
    }

    public String getMinKey() {
        if(treeMap.isEmpty()) {
            return "" ;
        }
        return treeMap.firstEntry().getKey().key ;
    }
}