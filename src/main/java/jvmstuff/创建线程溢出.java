package jvmstuff;

import basic.Threads;

import java.util.concurrent.TimeUnit;

public class 创建线程溢出 {
    public static void main(String[] args) {
        for (int i = 0; i < 3000; i++) {
            Threads.startThread(() -> {
                try {
                    TimeUnit.MINUTES.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
