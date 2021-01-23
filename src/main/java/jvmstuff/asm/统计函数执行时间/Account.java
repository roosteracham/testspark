package jvmstuff.asm.统计函数执行时间;

import java.util.concurrent.TimeUnit;

public class Account {
    public void operation() throws InterruptedException {
        System.out.println("operation...");
        TimeUnit.SECONDS.sleep(2);
    }
}
