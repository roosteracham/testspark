package jvmstuff;

public class G1GCTest {
    public static void main(String[] args) {
        int i = 1;
        while (true) {
            System.out.println(i);
            byte[] bytes = new byte[i++ * 1024 * 1024];
        }
    }
}
