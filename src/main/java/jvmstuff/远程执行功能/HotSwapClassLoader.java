package jvmstuff.远程执行功能;

public class HotSwapClassLoader extends ClassLoader {
    public HotSwapClassLoader() {
        super(HotSwapClassLoader.class.getClassLoader());
    }

    public Class loadBytes(byte[] classByte) {
        return defineClass(null, classByte, 0, classByte.length);
    }
}
