package 刷题.真题.携程;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *   设计一个数据结构，实现LRU Cache的功能(Least Recently Used – 最近最少使用缓存)。它支持如下2个操作： get 和 put。
 *
 * int get(int key) – 如果key已存在，则返回key对应的值value（始终大于0）；如果key不存在，则返回-1。
 * void put(int key, int value) – 如果key不存在，将value插入；如果key已存在，则使用value替换原先已经存在的值。如果容量达到了限制，LRU
 * Cache需要在插入新元素之前，将最近最少使用的元素删除。
 *
 * 请特别注意“使用”的定义：新插入或获取key视为被使用一次；而将已经存在的值替换更新，不算被使用。
 *
 * 限制：请在O(1)的时间复杂度内完成上述2个操作。
 */
public class LRU_Cache {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int cap = Integer.parseInt(scanner.nextLine());
        myLRU = new MyLRU(16, cap);


        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(" ");
            if ("p".equals(line[0])) {
                put(Integer.parseInt(line[1]), Integer.parseInt(line[2]));
            } else {
                System.out.println(get(Integer.parseInt(line[1])));
            }
        }
    }
    static int get(int key) {
        if (myLRU.containsKey(key)) {
            return (int) myLRU.get(key);
        } else {
            return  -1;
        }
    }
    private static MyLRU myLRU ;
    static void put(int key, int value) {
        myLRU.put(key, value);
    }
    static class MyLRU<K, V> extends LinkedHashMap<K, V> {

        private int cacheSize;

        public MyLRU(int initialCapacity, int cacheSize) {
            super(initialCapacity, 0.75f, true);
            this.cacheSize = cacheSize;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return this.size() > cacheSize;
        }
    }
}
