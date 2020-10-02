package basic;

public class Threads {
    public static void startThread(Runnable runnable) {
        new Thread(runnable).start();
    }
}
