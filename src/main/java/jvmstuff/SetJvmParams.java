package jvmstuff;

import basic.Threads;
import domain.User;

public class SetJvmParams {

    public static void main(String[] args) {

//        allocateOnHeap();

//        Threads.startThread(() -> allocateOnStack());
        if (args != null) {
            for (String arg : args) {
                System.out.println(arg);
            }
        }

        System.out.println("+Xmx: " + Runtime.getRuntime().maxMemory() / 1024 / 1024);

        try {
        handle();
    } catch (StackOverflowError e) {
        e.printStackTrace();
        System.out.println(counter);
    }
    }

    //-XX:+PrintGC

    /**
     * 堆上分配
     */
    static void allocateOnHeap() {
        for (int i = 0; i < 100000000; i++) {
            new User("a", i * 1.0, i);
        }
    }

    /**
     * 动态生成类
     */
    void dynamicGeneClass() {

    }

    /**
     * 栈层数
     */
    static int counter=0;
    static void handle() {
            counter++;
            handle();
    }
}
