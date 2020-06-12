package org.zsf.caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

public class CaffeineDemo {

    public static void main(String[] args) throws InterruptedException {
        Cache<String, String> cache = Caffeine.newBuilder()
                .initialCapacity(1)
                .expireAfterWrite(1, TimeUnit.SECONDS)
                .maximumSize(1)
                .removalListener((k, v, cause) -> {
                    System.out.println(k + ": " + v + " removed due to " + cause.name());
                })
                .softValues()
                .build(String::toUpperCase);

        cache.put("a", "asd");

        System.out.println(cache.getIfPresent("b"));
        System.out.println(cache.get("b", e -> e));
        System.out.println(cache.getIfPresent("b"));
        System.out.println(cache.getIfPresent("a"));

        TimeUnit.SECONDS.sleep(2);
        System.out.println(cache.getIfPresent("a"));
        System.out.println(cache.getIfPresent("c"));
    }
}
