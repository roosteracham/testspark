package basic.concurrency.atomic;

import basic.Threads;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.LockSupport;

public class ABAProblem {

    public static void main(String[] args) {
//        aba();
        solveAba();
    }

    private static void solveAba() {
        AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference<>(1, 0);


        Threads.startThread(() -> {
            int[] stampHolder = new int[1];
            Integer i = stampedReference.get(stampHolder);
            int stamp = stampHolder[0];
            System.out.println(Thread.currentThread().getName() + " value: " + i + ", stamp: " + stamp);


            LockSupport.parkNanos(1000000000);

            if (stampedReference.compareAndSet(i, 2, stamp, stamp + 1)) {
                i = stampedReference.get(stampHolder);
                stamp = stampHolder[0];
                System.out.println(Thread.currentThread().getName() + " value: " + i + ", stamp: " + stamp);
            } else {
                System.out.println(Thread.currentThread().getName() + " update fail: ");
            }
        });

        Threads.startThread(() -> {

            int[] stampHolder = new int[1];
            Integer i = stampedReference.get(stampHolder);
            int stamp = stampHolder[0];
            System.out.println(Thread.currentThread().getName() + " value: " + i + ", stamp: " + stamp);

            if (stampedReference.compareAndSet(i, 2, stamp, stamp + 1)) {
                i = stampedReference.get(stampHolder);
                stamp = stampHolder[0];
                System.out.println(Thread.currentThread().getName() + " value: " + i + ", stamp: " + stamp);
            } else {
                System.out.println(Thread.currentThread().getName() + " update fail: ");
            }

            int newStamp = stamp + 1; // 0 是0 的话还是会有aba问题
            if (stampedReference.compareAndSet(i, 1, stamp, newStamp)) {
                i = stampedReference.get(stampHolder);
                stamp = stampHolder[0];
                System.out.println(Thread.currentThread().getName() + " value: " + i + ", stamp: " + stamp);
            } else {
                System.out.println(Thread.currentThread().getName() + " update fail: ");
            }
        });
    }

    private static void aba() {
        AtomicInteger integer = new AtomicInteger(1);

        new Thread(() -> {
            int i = integer.get();
            System.out.println(Thread.currentThread().getName() + " value: " + i);

            LockSupport.parkNanos(1000000000);

            if (integer.compareAndSet(i, 2)) {
                System.out.println(Thread.currentThread().getName() + " update value: " + integer.get());
            } else {
                System.out.println(Thread.currentThread().getName() + " update fail: ");
            }
        }).start();

        new Thread(() -> {

            int i = integer.get();
            System.out.println(Thread.currentThread().getName() + " value: " + i);

            if (integer.compareAndSet(i, 2)) {
                System.out.println(Thread.currentThread().getName() + " update value: " + integer.get());
            } else {
                System.out.println(Thread.currentThread().getName() + " update fail: ");
            }

            i = integer.get();
            if (integer.compareAndSet(i, 1)) {
                System.out.println(Thread.currentThread().getName() + " update value: " + integer.get());
            } else {
                System.out.println(Thread.currentThread().getName() + " update fail: ");
            }
        }).start();
    }
}
