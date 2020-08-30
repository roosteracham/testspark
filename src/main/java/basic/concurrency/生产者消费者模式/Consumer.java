package basic.concurrency.生产者消费者模式;

import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Consumer {

    public void consume(List<String> list) {
        int i = 0;
        while (i++ < 100) {
            synchronized (list) {
                try {
                    if (CollectionUtils.isEmpty(list)) {
                        list.wait();
                    }
                    System.out.println(Thread.currentThread().getName() + "-" + list.remove(0));
                    list.notifyAll();
//                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
