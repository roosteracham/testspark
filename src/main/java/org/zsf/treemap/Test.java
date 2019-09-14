package org.zsf.treemap;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class Test {

    public static void main(String[] args) {
        Map<String, Integer> map = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        });
//        map.put(1,2);
//        map.put(5,3);
//        map.put(3,3);
        System.out.println(map);
    }
}
