package jvmstuff;

import java.nio.ByteBuffer;

public class 直接内存溢出 {

    public static void main(String[] args) {
        for (int i = 0; i < 1024; i++) {
            ByteBuffer buffer = ByteBuffer.allocateDirect(1024 * 1024);
            System.out.println(i);
//            System.gc();
        }
    }
}
