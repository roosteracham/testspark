package jvmstuff.引用;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class PhanRefTest {
    public static void main(String[] args) {
        PhantomReference<Object> phantomReference = new PhantomReference<>(1, new ReferenceQueue<>());
    }
}
