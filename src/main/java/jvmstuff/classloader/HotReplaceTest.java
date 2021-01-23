package jvmstuff.classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class HotReplaceTest {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, InterruptedException, ClassNotFoundException {
        while (true) {
            HotReplace hotReplace = new HotReplace();
            Class<?> demo = hotReplace.loadClass("jvmstuff.classloader.ClassDemoA");
            Object instance = demo.newInstance();
            Method method = demo.getDeclaredMethod("hot", new Class[]{});
            method.setAccessible(true);
            method.invoke(instance, new Object[0]);
            TimeUnit.SECONDS.sleep(5);
        }
    }
}
