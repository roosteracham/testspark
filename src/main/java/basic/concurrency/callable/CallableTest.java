package basic.concurrency.callable;

import io.netty.util.concurrent.DefaultThreadFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CallableTest extends Thread {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        new CallableTest().start();
    }

    private static void test() throws InterruptedException, ExecutionException, TimeoutException {
        Callable<String> callable = () -> {
            throw new RuntimeException("123");
        };
        ExecutorService service = new ThreadPoolExecutor(10, 10, 0, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(),
                new DefaultThreadFactory("testcallable"));

        Future<String> future = service.submit(callable);
        String s = future.get(10, TimeUnit.SECONDS);
        System.out.println(s);
        System.out.println(future.isCancelled());
        System.out.println(future.isDone());
    }

    @Override
    public void run() {
        try {
            CallableTest.test();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        super.run();
    }
}
