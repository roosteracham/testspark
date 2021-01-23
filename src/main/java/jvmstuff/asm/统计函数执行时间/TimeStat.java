package jvmstuff.asm.统计函数执行时间;

public class TimeStat {
    static ThreadLocal<Long> t = new ThreadLocal<>();
    public static void start() {
        t.set(System.currentTimeMillis());
    }

    public static void end() {
        long during = System.currentTimeMillis() - t.get();
        System.out.println(Thread.currentThread().getStackTrace()[2] + " spend: " + during);
    }
}
