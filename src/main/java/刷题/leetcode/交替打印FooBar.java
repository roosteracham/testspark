package 刷题.leetcode;

import basic.Threads;

public class 交替打印FooBar {
    public static void main(String[] args) throws InterruptedException {
        FooBar fooBar = new FooBar(2);
        Threads.startThread(() -> {
            try {
                fooBar.foo(() -> fooBar.foo());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Threads.startThread(() -> {
            try {
                fooBar.bar(() -> fooBar.bar());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}

class FooBar {
    private int n;

    public FooBar(int n) {
        this.n = n;
    }


    public void foo() {
        for (int i = 0; i < n; i++) {
            System.out.print("foo");
        }
    }

    public void bar() {
        for (int i = 0; i < n; i++) {
            System.out.println("bar");
        }
    }

    static volatile byte flag = 0;

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            // printFoo.run() outputs "foo". Do not change or remove this line.
            synchronized (FooBar.class) {

                if (flag == 0) {
                    printFoo.run();
                    wait();
                }
                flag = 1;
                notifyAll();
            }
        }
    }

        public void bar (Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {

                // printBar.run() outputs "bar". Do not change or remove this line.
                synchronized (FooBar.class) {

                    if (flag == 1) {
                        printBar.run();
                        wait();
                    }
                    flag = 0;
                    notifyAll();
                }

            }
        }
    }
