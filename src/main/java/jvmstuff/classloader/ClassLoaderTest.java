package jvmstuff.classloader;

import java.io.IOException;
import java.io.InputStream;

public class ClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
//        customClassLoader();
        String a = "1";
        ClassLoader loader = ClassLoaderTest.class.getClassLoader();
        System.out.println(a.getClass().getClassLoader());
        while (loader != null) {
            System.out.println(loader);
            loader = loader.getParent();
        }
    }

    private static void customClassLoader() throws InstantiationException, IllegalAccessException,
            ClassNotFoundException {
        ClassLoader myClassLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream inputStream = getClass().getResourceAsStream(fileName);
                    if (inputStream == null) {
                        return super.loadClass(name);
                    }

                    byte[] bytes = new byte[inputStream.available()];
                    inputStream.read(bytes);
                    return defineClass(name, bytes, 0, bytes.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException();
                }
            }
        };

        Object instance = myClassLoader.loadClass("jvmstuff.classloader.ClassLoaderTest").newInstance();
        System.out.println(instance.getClass());

        System.out.println(instance instanceof ClassLoaderTest);
    }
}
