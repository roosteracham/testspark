package jvmstuff;

public class VolatileTest {

    private volatile boolean a = false;

    public void test() {
        int b = 1;
        a = true;
        int c = b + 1;
    }
}
