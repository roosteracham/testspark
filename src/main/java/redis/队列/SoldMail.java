package redis.队列;

import basic.Threads;
import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.CollectionUtils;
import redis.MailContent;
import redis.RedisUtil;
import redis.clients.jedis.Jedis;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SoldMail {
    static Jedis jedis = RedisUtil.getJedis();

    public static final String key = "sold:mail";

    Long push(String seller, int item, String buyer, double price) {
        MailContent content = new MailContent(seller, item + "", price, buyer);
        return jedis.rpush(key, JSON.toJSONString(content));
    }

    Long push(MailContent content) {
        return jedis.rpush(key, JSON.toJSONString(content));
    }

    void sendMail() throws InterruptedException {
        while (true) {
            List<String> list = jedis.brpop(30, key);
            if (CollectionUtils.isEmpty(list)) {
                continue;
            }
            TimeUnit.SECONDS.sleep(2);
            MailContent content = JSON.parseObject(list.get(1), MailContent.class);
            doSend(content);
        }
    }

    ThreadLocal<DateFormat> df = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"));

    private void doSend(MailContent content) {
        System.out.println(
                content.getBuyer() + " buy " + content.getItem() + " from " +
                        content.getSeller() + " at " + df.get().format(content.getTime())
        );
    }

    public static void main(String[] args) throws InterruptedException {
        SoldMail soldMail = new SoldMail();
        soldMail.sendMail();
//        for (int i = 0; i < 20; i++) {
//            MailContent mailContent = new MailContent("s" + i, "item" + i, i * i, "b" + i);
//            soldMail.push(mailContent);
//        }
    }
}
