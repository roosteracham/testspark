package jvmstuff.clz;

public class ClzFile {
    final int a = 15;
    private String s = "123";
    private int i = 0;
    public void test() {
        String c = "2333";
        int b = 4 / 3;
        System.out.println(b + c);
    }

    public synchronized void add1() {
        i++;
    }

    public int add() {
        int aa = 500;
        int bb = 200;
        int c = 50;
        return (aa + bb) / c;
    }
}
