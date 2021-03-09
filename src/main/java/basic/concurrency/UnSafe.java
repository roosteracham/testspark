package basic.concurrency;

import domain.User;
import sun.misc.Unsafe;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiFunction;

public class UnSafe {

    static Unsafe unsafe;

    {
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


    public static Unsafe getUnsafe() {
        return unsafe;
    }
}
