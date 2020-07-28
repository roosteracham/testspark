package basic.concurrency.同步工具类;

public class BarrierTest {
    public static void main(String[] args) {
        int a = 0;
        switch (a) {
            case 0:
                break;
            case 1:
                String test = "1";
                System.out.println(test);
            case 2:
                test ="2";
                System.out.println(test);
        }
    }
}
