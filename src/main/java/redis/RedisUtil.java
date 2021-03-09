package redis;

import redis.clients.jedis.Jedis;

public class RedisUtil {
    public static Jedis jedis;

    static  {
        jedis = new Jedis("8.131.65.50");
        jedis.auth("rott123");
    }

    public static Jedis getJedis() {
        return jedis;
    }
}
