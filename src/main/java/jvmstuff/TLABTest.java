package jvmstuff;


public class TLABTest {

    //-XX:+UseTLAB -Xcomp -XX:-BackgroundCompilation -XX:-DoEscapeAnalysis -server
    public static void main(String[] args) {
        long a = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            alloc();
        }
        System.out.println(System.currentTimeMillis() - a);
    }

    static void alloc() {
        byte[] bytes = new byte[2];
        bytes[0] = 1;
    }
}
