package basic.classloader;

import basic.Threads;
import basic.collection.LinkedHashMapTest;
import domain.User;

import java.util.concurrent.TimeUnit;

public class TraceLoadClass {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("abc");
        TimeUnit.SECONDS.sleep(5);
        new User();
        TimeUnit.SECONDS.sleep(5);
        Threads[] threads = new Threads[10];
        TimeUnit.SECONDS.sleep(5);
        System.out.println(LinkedHashMapTest.a);
    }
}
