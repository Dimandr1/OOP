package ru.nsu.stolyarov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class NumberTest {
    @Test
    void test() {

        Number testNum = new Number(5);
        assertEquals("5.0", testNum.print());
        assertEquals(5, testNum.eval(""));
        assertEquals(5, testNum.eval("asdg = 14"));
        testNum = (Number) testNum.derivative("x");
        assertEquals(0, testNum.eval(""));

        testNum = new Number(-20);
        assertEquals("(-20.0)", testNum.print());
        assertEquals(-20, testNum.eval(""));
        assertEquals(-20, testNum.eval("bsb = 14; x = 5; y = -52"));
        testNum = (Number) testNum.derivative("x");
        assertEquals(0, testNum.eval(""));

        testNum = new Number(0);
        assertEquals("0.0", testNum.print());
        assertEquals(0, testNum.eval(""));
        testNum = (Number) testNum.derivative("x");
        assertEquals(0, testNum.eval(""));


    }

}