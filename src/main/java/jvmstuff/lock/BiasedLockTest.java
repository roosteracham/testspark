package jvmstuff.lock;

import java.util.Vector;

/**
 * 偏向锁默认是关的
 */
public class BiasedLockTest {
    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        long l = System.currentTimeMillis();
        int count = 0;
        int startNum = 0;
        while (count < 10000000) {
            vector.add(startNum);
            startNum += 2;
            count++;
        }
        System.out.println(System.currentTimeMillis() - l);
    }
}
