package jvmstuff;

public class OOMTest {

    public static void main(String[] args) {
        for (int i = 1; i < 10; i++) {
            byte[] _1m = new byte[i * 1024 * 1024];
        }
    }
}
