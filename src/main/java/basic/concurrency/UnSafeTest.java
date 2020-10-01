package basic.concurrency;

import domain.User;
import org.apache.commons.lang.BooleanUtils;
import sun.misc.Unsafe;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiFunction;

public class UnSafeTest {

    Unsafe unsafe;

    public UnSafeTest() {
        try {

            //java.lang.SecurityException: Unsafe
//        Unsafe unsafe = Unsafe.getUnsafe();
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            // 静态方法传null 或对象都可以，
            // 实力方法只能穿对象实例，获取对象对应属性值，否则报空指针
            unsafe = (Unsafe) field.get(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private volatile int count = 0;

    static UnSafeTest unSafeTest = new UnSafeTest();

    volatile int flag;

    public static void main(String[] args) throws NoSuchFieldException {
        // 只为对象分配内存，但不调用构造函数
        Unsafe unsafe = unSafeTest.unsafe;
//        创建对象(unsafe);
//        threx(unsafe);

//        分配对外内存(unsafe);

//        unSafeTest.CAS(unsafe);

        unSafeTest.交替打印(unsafe);
    }

    private static void 交替打印(Unsafe unsafe) throws NoSuchFieldException {
        long offset = unsafe.objectFieldOffset(unSafeTest.getClass().getDeclaredField("flag"));

        for (int i = 0; i < 100; i++) {

            new Thread(() -> {
                swap(unsafe, true, offset);
            }).start();
            new Thread(() -> {
                swap(unsafe, false, offset);
            }).start();
        }
    }

    /**
     * cas 能保证原子性，但是存在ABA问题
     * @param unsafe
     * @param swapped
     * @param offset
     */
    private static void swap(Unsafe unsafe, boolean swapped, long offset) {
        int b = unSafeTest.flag;
        System.out.println(Thread.currentThread().getName() + " : " + b);
        while (!unsafe.compareAndSwapInt(unSafeTest, offset, b, swapped ? 0 : 1)) {
            b = unSafeTest.flag;
        }
        System.out.println(Thread.currentThread().getName() + " : " + b);
    }

    /**
     * cas 实现锁
     * @param unsafe
     * @throws NoSuchFieldException
     */
    private void CAS(Unsafe unsafe) throws NoSuchFieldException {
        Field count = UnSafeTest.class.getDeclaredField("count");
        long offset = unsafe.objectFieldOffset(count);

        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {

            executorService.submit(() -> {
                increment(unsafe, offset);
                System.out.println(Thread.currentThread().getName() + ": " + unSafeTest.count);
            });
        }
        executorService.shutdown();
    }

    public void increment(Unsafe unsafe, long offset) {
        int before = count;
        while (!unsafe.compareAndSwapInt(unSafeTest, offset, before, before + 1)) {
            before = count;
        }
    }

    /**
     * 申请对外内存之后，需要手动释放内存
     *
     * @param unsafe
     */
    private static void 分配对外内存(Unsafe unsafe) {
        int size = 5, INT = 4;
        long memory = unsafe.allocateMemory(size * INT);
        BiFunction<Long, Integer, Long> function = (x, y) -> x + y * INT;
        for (int i = 0; i < size; i++) {
            System.out.println("point: " + function.apply(memory, i) + ", value: " + unsafe.getInt(function.apply(memory, i)));
        }
        System.out.println("========");
        for (int i = 0; i < size; i++) {
            unsafe.putInt(function.apply(memory, i), i * i + 1);
        }

        for (int i = 0; i < size; i++) {
            System.out.println("point: " + function.apply(memory, i) + ", value: " + unsafe.getInt(function.apply(memory, i)));
        }

        System.out.println("========");
        System.out.println(unsafe.getInt(function.apply(memory, 0)));
        unsafe.freeMemory(function.apply(memory, 0));
//        unsafe.freeMemory(function.apply(memory, 1));

        System.out.println("========");
        System.out.println(unsafe.getInt(function.apply(memory, 0)));
        System.out.println(unsafe.getInt(function.apply(memory, 1)));
        System.out.println(unsafe.getInt(function.apply(memory, 2)));
        System.out.println(unsafe.getInt(function.apply(memory, 6)));
    }

    static void threxNeedCatch(Unsafe unsafe) throws IOException {
        throw new IOException();
    }

    // 抛出受检查的异常，但不必在方法签名上显示抛出，或者try catch
    static void threx(Unsafe unsafe) {
        unsafe.throwException(new IOException());
    }

    private static void 创建对象(Unsafe unsafe) throws InstantiationException, NoSuchFieldException {
        Object instance = unsafe.allocateInstance(User.class);
        System.out.println(instance);
        Field name = User.class.getDeclaredField("name");
        unsafe.putObject(instance, unsafe.objectFieldOffset(name), "abc");
        System.out.println(instance);
    }
}
