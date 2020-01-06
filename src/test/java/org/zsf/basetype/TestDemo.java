package org.zsf.basetype;

import org.junit.Test;

public class TestDemo {

    @Test
    public void test1() {
        float a = 0.2f;
        System.out.println(a > 0.2);
        System.out.println(a > (float)0.2);
        double b = 0.2;
        System.out.println(b > 0.2);
    }
}
