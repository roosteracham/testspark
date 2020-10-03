package basic.concurrency.atomic;

import basic.Threads;

/**
 * https://juejin.im/post/6844903841964507150
 *
 * https://www.jianshu.com/p/c3c108c3dcfd
 */
public class 伪共享 {
    public static void main(String[] args) throws InterruptedException {
        Point point = new Point();
        long start = System.currentTimeMillis();
        Thread thread1 = Threads.startThread(() -> {
            for (long i = 0; i < 100000000; i++) {
                point.x++;
            }
        });
        Thread thread = Threads.startThread(() -> {
            for (long i = 0; i < 100000000; i++) {
                point.y++;
            }
        });

        thread1.join();
        thread.join();

        System.out.println("elapsed: " + (System.currentTimeMillis() - start));
    }
}

//@sun.misc.Contended  放在这里没用
class Point {
    volatile long x;
    //    long a,b,c,d,e,f,g;
    @sun.misc.Contended("x")
    volatile long y;
}