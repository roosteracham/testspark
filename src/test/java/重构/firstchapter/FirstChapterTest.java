package 重构.firstchapter;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FirstChapterTest {

    @Test
    public void test1() {
        List<Rental> rentals = new ArrayList<>();
        CustomerV4 c1 = new CustomerV4("c1", rentals);
        Rental r1 = new Rental(new Movie("m1", 1), 2);
        rentals.add(r1);
        Rental r2 = new Rental(new Movie("m2", 2), 2);
        rentals.add(r2);
        System.out.printf(c1.statement());
    }
}
