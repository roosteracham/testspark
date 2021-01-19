package jvmstuff;

import java.util.concurrent.TimeUnit;

public class 字符串内存泄露 {
    public static void main(String[] args) throws InterruptedException {
        String a = "1234";
        String s = a.substring(2);
////        a = a.replaceFirst("3", "4");
//        System.out.println(a);
//        System.out.println(s);


        System.out.println(System.identityHashCode(a + Integer.toString(0)));
        System.out.println(System.identityHashCode((a + Integer.toString(0)).intern()));
        TimeUnit.SECONDS.sleep(1);
        System.gc();
        System.out.println(System.identityHashCode((a + Integer.toString(0)).intern()));
    }
}
