package redis;

import redis.clients.jedis.Jedis;

public class FirstDemo {

    public static void main(String[] args) {
        Jedis jedis = new Jedis();
//        jedis.connect();
        jedis.set("a", "1");
        System.out.println(jedis.get("a"));
        System.out.println(jedis.ping());
        jedis.close();
    }
}
