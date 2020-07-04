package basic.泛型;

import java.util.ArrayList;
import java.util.List;

public class 泛型擦除 {

//    static void method(List<String> list) {
//        System.out.println("List<String> list");
//    }

    static int method(List<Integer> list) {
        System.out.println("List<Integer> list");
        return 1;
    }

    public static void main(String[] args) {
//        method(new ArrayList<String>());
        method(new ArrayList<Integer>());
    }
}
