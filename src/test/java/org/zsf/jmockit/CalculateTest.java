package org.zsf.jmockit;

import jmockit.Calculate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculateTest {
    private static Calculate calculate = null;

    @BeforeClass
    public static void beforeClass() {
        System.out.println("------------------------BeforeClass------------------------");
        calculate = new Calculate();
    }
    @AfterClass
    public static void afterClass() {
        System.out.println("------------------------AfterClass------------------------");
        calculate = null;
    }

    @Before
    public void setUp() throws Exception {
        System.out.println("-------Before Method-------");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("-------After Method-------");
    }

    @Test
    public void add() throws Exception {
        assertEquals(10, calculate.add(7, 3));
    }

    @Test
    public void subtract() throws Exception {
        assertEquals(4, calculate.subtract(7, 3));
    }
}
