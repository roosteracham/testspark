package basic;

public class Threads {
    public static Thread startThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.start();
        return thread;
    }
}
