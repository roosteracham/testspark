package jvmstuff.classloader;

public class SubClass extends SuperClass {
    static {
        System.out.println("sub load");
    }
}
