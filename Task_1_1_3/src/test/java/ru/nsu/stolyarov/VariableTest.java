package ru.nsu.stolyarov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class   VariableTest {
    @Test
    void test() {

        Variable testVar = new Variable("x");
        assertEquals("x", testVar.print());
        assertEquals(14, testVar.eval("x = 14"));
        Expression testExpr = testVar.derivative("x");
        assertEquals(1, testExpr.eval(""));

        testVar = new Variable("xyz");
        assertEquals("xyz", testVar.print());
        assertEquals(-14, testVar.eval("xyz = -14"));
        testExpr = testVar.derivative("xyz");
        assertEquals(1, testExpr.eval("x = 5"));
        assertEquals(1, testExpr.eval("xyz = 10"));

        testVar = new Variable("xyz");
        testExpr = testVar.derivative("x");
        assertEquals(0, testExpr.eval("x = 5"));

        testVar = new Variable("abab");
        assertEquals("abab", testVar.print());
        assertEquals(-505123, testVar.eval("abab = -505123"));
        assertEquals(-505, testVar.eval("ababab = 123; abab = -505"));

        testVar = new Variable("abab");
        assertEquals("abab", testVar.print());
        assertEquals(-505123.123, testVar.eval("abab = -505123.123"));

        testVar = new Variable("abab");
        assertEquals("abab", testVar.print());
        assertEquals(5123.1, testVar.eval("abab = 5123.1"));

        testVar = new Variable("abab");
        assertEquals("abab", testVar.print());
                    assertEquals(0, testVar.eval("vava = -505123"));
        assertEquals(-505, testVar.eval("ababab = 123; abab = -505"));

    }
}