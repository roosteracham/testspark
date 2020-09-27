package org.zsf.jmockit;

import jmockit.Calculate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.modules.junit4.PowerMockRunner.*;

import static org.junit.Assert.assertEquals;

@PrepareForTest(Calculate.class)
@RunWith(PowerMockRunner.class)
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
        Calculate mock = mock(Calculate.class);
        when(mock.get()).thenReturn("123");
        System.out.println(mock.get());
    }

    @Test
    public void subtract() throws Exception {
        assertEquals(4, calculate.subtract(7, 3));
    }
}
