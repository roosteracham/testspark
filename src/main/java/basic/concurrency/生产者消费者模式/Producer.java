package basic.concurrency.生产者消费者模式;

import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Producer {

    public void produce(List<String> list) {
        int i = 0;
        while (i++ < 100) {
            synchronized (list) {
                try {
                    if (CollectionUtils.isNotEmpty(list)) {
                        list.wait();
                    }
                    list.add("abc-" + i);
                    list.notifyAll();
//                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
