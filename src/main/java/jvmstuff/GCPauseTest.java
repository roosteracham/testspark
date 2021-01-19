package jvmstuff;

import basic.Threads;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class GCPauseTest {

    public static void main(String[] args) {
        Threads.startThread(() -> {

            Map<Object, Object> map = new HashMap<>();
            while (true) {
                if (map.size() / 2/ 1024 >= 900) {
                    map.clear();
                    System.out.println("clear map");
                }

                for (int i = 0; i < 100; i++) {
                    map.put(System.nanoTime(), new byte[512]);
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
//        long s = System.currentTimeMillis();
//        Threads.startThread(() -> {
//            try {while (true) {
//
//                TimeUnit.MICROSECONDS.sleep(100);
//                long t = System.currentTimeMillis() - s;
//                System.out.println(t / 1000 + "." + t % 100);
//            }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
    }
}
