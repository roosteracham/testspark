package org.zsf.caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Expiry;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.concurrent.TimeUnit;

public class CaffeineDemo {

    public static void main(String[] args) throws InterruptedException {
        LoadingCache<String, String> cache = Caffeine.newBuilder()
                .initialCapacity(1)
                .expireAfter(new Expiry<String, String>() {
                    @Override
                    public long expireAfterCreate(@NonNull String key, @NonNull String value, long currentTime) {
                        return 1000 * 1000L;
                    }

                    @Override
                    public long expireAfterUpdate(@NonNull String key, @NonNull String value, long currentTime,
                                                  @NonNegative long currentDuration) {
                        return Long.MAX_VALUE;
                    }

                    @Override
                    public long expireAfterRead(@NonNull String key, @NonNull String value, long currentTime, @NonNegative long currentDuration) {
                        return Long.MAX_VALUE;
                    }
                })
                .maximumSize(1)
                .removalListener((k, v, cause) -> {
                    System.out.println(k + ": " + v + ", removed due to " + cause.name());
                })
                .softValues()
                .build(String::toUpperCase);

        cache.put("a", "asd");

        System.out.println(cache.get("b"));
        System.out.println(cache.get("b", e -> e));
        System.out.println(cache.get("b"));
        System.out.println(cache.get("a"));

        TimeUnit.SECONDS.sleep(2);
        System.out.println(cache.get("a"));
        System.out.println(cache.get("b"));
        System.out.println(cache.get("c"));
    }
}
