package redis;

import basic.Threads;
import redis.clients.jedis.Jedis;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class 读写一致 {

    public static void main(String[] args) throws InterruptedException {
        读写一致 instance = new 读写一致();
        Threads.startThread(() -> {
            try {
                instance.update("11", "val11");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread.sleep(1000);
        for (int i = 0; i < 100; i++) {
            Threads.startThread(() -> instance.get("11"));
        }
    }

    LinkedList<ConsisTask> queue;

    static Jedis jedis;

    ScheduledExecutorService scheduledExecutorService;

    public 读写一致() {
        jedis = new Jedis("8.131.65.50");
        jedis.auth("rott123");
        queue = new LinkedList<>();
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            if (!queue.isEmpty()) {
                ConsisTask consisTask = queue.pop();
                String val = getFromDB(consisTask.key);
                setCache(consisTask.key, val);
                consisTask.thread.notify();
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    public String get(String key) {
        String s = jedis.get(key);
        // 如果数据库中没有数据，放到一个队列里面
        if (s == null) {
            ConsisTask task = new ConsisTask(key, Thread.currentThread());
            queue.push(task);
            boolean callDB = true;
            try {
                Thread.currentThread().wait(1000);
            } catch (InterruptedException e) {
                s = task.val;
                if (s != null) {
                    System.out.println(Thread.currentThread().getName() + " queue notify get from db" + ", val: " + s);
                    callDB = false;
                }
            }
            if (callDB) {
                s = getFromDB(key);
                System.out.println(Thread.currentThread().getName() + " to get from db" + ", val: " + s);
                setCache(key, s);
            }
        }
        return s;
    }

    void update(String key, String val) throws InterruptedException {
        deleteCache(key);
        TimeUnit.SECONDS.sleep(1);
        updateDB(key, val);
        System.out.println("update db");
        queue.push(new ConsisTask(key, Thread.currentThread()));
    }

    static Map<String, String> dbVal = new ConcurrentHashMap<>();

    private void updateDB(String key, String val) {
        dbVal.put(key, val);
    }

    private void deleteCache(String key) {
        jedis.del(key);
    }

    private String getFromDB(String key) {
        return dbVal.get(key);
    }

    private void setCache(String key, String val) {
        jedis.set(key, val);
    }

    static class ConsisTask {
        public String key;

        public String val;

        public ConsisTask(String key, Thread thread) {
            this.key = key;
            this.thread = thread;
        }

        public Thread thread;
    }
}
