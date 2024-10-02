package ru.nsu.stolyarov;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubTest {
    @Test
    void test(){
        Sub testSub = new Sub(new Number(5), new Number(20));
        assertEquals("(5-20)", testSub.print());
        assertEquals(-15, testSub.eval(""));
        Expression testExpr = testSub.derivative("x");
        assertEquals(0, testExpr.eval(""));
        assertEquals("(0-0)", testExpr.print());

        testSub = new Sub(new Number(5), new Number(-20));
        assertEquals("(5-(-20))", testSub.print());
        assertEquals(25, testSub.eval(""));
        testExpr = testSub.derivative("x");
        assertEquals(0, testExpr.eval(""));
        assertEquals("(0-0)", testExpr.print());

        testSub = new Sub(new Number(-5), new Number(20));
        assertEquals("((-5)-20)", testSub.print());
        assertEquals(-25, testSub.eval(""));
        testExpr = testSub.derivative("x");
        assertEquals(0, testExpr.eval(""));
        assertEquals("(0-0)", testExpr.print());

        testSub = new Sub(new Variable("x"), new Number(20));
        assertEquals("(x-20)", testSub.print());
        assertEquals(-50, testSub.eval("x = -30"));
        testExpr = testSub.derivative("x");
        assertEquals(1, testExpr.eval("x = 5"));
        assertEquals("(1-0)", testExpr.print());

        testSub = new Sub(new Variable("x"), new Variable("xyz"));
        assertEquals("(x-xyz)", testSub.print());
        assertEquals(-50, testSub.eval("x = -30; xyz = 20"));
        testExpr = testSub.derivative("xyz");
        assertEquals(-1, testExpr.eval("x = 5; xyz = 50"));
        assertEquals("(0-1)", testExpr.print());
    }
}