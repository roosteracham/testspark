package org.zsf.jmockit;

import mockit.Expectations;
import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

public class HelloJMockitTest {
    @Test
    public void sayHelloCH() {
        new Expectations(Locale.class) {
            {
                Locale.getDefault();
                result = Locale.CHINA;
            }
        };
        // 断言说中文
        Assert.assertTrue("你好世界".equals((new HelloJMockit()).sayHello()));
    }

    @Test
    public void sayHelloUS() {
        new Expectations(Locale.class) {
            {
                Locale.getDefault();
                result = Locale.US;
            }
        };
        // 断言说英文
        Assert.assertTrue("Hello World".equals((new HelloJMockit()).sayHello()));
    }
}