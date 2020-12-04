package redis.补全;

import org.apache.commons.collections.CollectionUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LatestContact {

    private static Jedis jedis = new Jedis("8.131.65.50");

    public LatestContact() {
        jedis.auth("rott123");
    }

    public static void main(String[] args) throws IOException {
        LatestContact contact = new LatestContact();
        String user = "u1";
        contact.add(user, "c1");
        contact.add(user, "c2");
        contact.add(user, "c2");
        System.out.println(contact.match(user, "c"));
    }

    private void add(String user, String contact) throws IOException {
        String key = "recent-" + user;
        Pipeline pipelined = jedis.pipelined();
        pipelined.multi();
        pipelined.lrem(key, 0, contact);
        pipelined.lpush(key, contact);
        pipelined.ltrim(key, 0, 100);
        pipelined.exec();
        pipelined.close();
    }

    private void remove(String user, String contact) {
        String key = "recent-" + user;
        jedis.lrem(key, 0, contact);
    }

    private List<String> match(String user, String prefix) {
        String key = "recent-" + user;
        List<String> stringList = jedis.lrange(key, 0, -1);
        return Optional.ofNullable(stringList).orElseGet(ArrayList::new)
                .stream().filter(x -> x.startsWith(prefix))
                .collect(Collectors.toList());
    }
}
