package basic.concurrency.future;

import io.netty.util.concurrent.DefaultThreadFactory;
import org.eclipse.jetty.util.BlockingArrayQueue;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompletionServiceTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(10, new DefaultThreadFactory("test"));
        ExecutorCompletionService<String> completionService =
                new ExecutorCompletionService<>(executor);

        for (int i = 0; i < 100; i++) {
            int j = i;
            completionService.submit(() -> Thread.currentThread().getName() + ", "+ j);
        }

        int k = 0;
        while (k++ < 100) {
            System.out.println(completionService.take().get());
        }
    }
}
