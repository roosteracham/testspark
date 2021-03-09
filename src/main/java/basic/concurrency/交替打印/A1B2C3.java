package basic.concurrency.交替打印;

import basic.Threads;
import basic.concurrency.UnSafe;
import sun.misc.Unsafe;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class A1B2C3 {

    static AtomicReference<String> val = new AtomicReference<>("A1");

    public static void main(String[] args) {
        Unsafe unsafe = UnSafe.getUnsafe();
        Threads.startThread(() -> {
            while (true) {
                while (!val.compareAndSet("A1", "B2")) {

                }
                System.out.println("A1");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Threads.startThread(() -> {
            while (true) {

                while (!val.compareAndSet("B2", "C3")) {

                }
                System.out.println("B2");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Threads.startThread(() -> {
            while (true) {

                while (!val.compareAndSet("C3", "A1")) {

                }
                System.out.println("C3");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
