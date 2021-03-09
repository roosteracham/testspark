package jvmstuff.classloader;

public class SuperClass {
    static {
        System.out.println("super load");
    }

    public static String val = "123";
}
