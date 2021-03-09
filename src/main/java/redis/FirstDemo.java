package redis;

import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.Collections;

public class FirstDemo {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("8.131.65.50");
        jedis.auth("rott123");
        System.out.println(jedis.ping());

        // 加锁
        jedis.set("1","1", "NX", "EX", 20L);

        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        String script2 = "return redis.call('get', KEYS[1])";

        // 解锁
        Object result = jedis.eval(script2, Collections.singletonList("1"), Collections.singletonList("1"));
        System.out.println(result);
        result = jedis.eval(script, Collections.singletonList("1"), Collections.singletonList("1"));
        System.out.println(result);
//        jedis.eval("redis.call('get', )");
        jedis.close();
    }
}
