package basic.concurrency.生产者消费者模式;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

public class TestDemo {

    // 这里是static， 不是static，请用同一个testDemo对象
    static List<String> list = Lists.newArrayList();

    public void produce() {
        int i = 0;
        while (i++ < 100) {
            synchronized (list) {
                try {
                    if (CollectionUtils.isNotEmpty(list)) {
                        list.wait();
                    }
                    list.add("abc");
                    list.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void consume() {
        int i = 0;
        while (i++ < 100) {
            synchronized (list) {
                try {
                    if (CollectionUtils.isEmpty(list)) {
                        list.wait();
                    }
                    System.out.println(list.remove(0));
                    list.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(() -> new TestDemo().produce()).start();
        new Thread(() -> new TestDemo().consume()).start();
    }
}
