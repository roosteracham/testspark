package basic.concurrency.threadlocal;

public class TestDemo {
    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        String s = threadLocal.get();
        System.out.println(s);
    }
}
