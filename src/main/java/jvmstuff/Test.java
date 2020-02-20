package jvmstuff;

public class Test implements Runnable{

    int a,b;

    public Test() {
    }

    public Test(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 200; i++) {
            new Thread(new Test(1, 2)).start();
            new Thread(new Test(2, 1)).start();
        }
    }

    @Override
    public void run() {
        synchronized (Integer.valueOf(a)) {
            synchronized (Integer.valueOf(b)) {
                System.out.println(a + b);
            }
        }
    }
}
