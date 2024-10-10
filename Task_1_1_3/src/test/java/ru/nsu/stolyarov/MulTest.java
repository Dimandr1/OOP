package ru.nsu.stolyarov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class MulTest {
    @Test
    void test() {
        Mul testMul = new Mul(new Number(5), new Number(20));
        assertEquals("(5.0*20.0)", testMul.print());
        assertEquals(100, testMul.eval(""));
        Expression testExpr = testMul.derivative("x");
        assertEquals(0, testExpr.eval(""));
        assertEquals("((0.0*20.0)+(5.0*0.0))", testExpr.print());

        testMul = new Mul(new Number(5), new Number(-20));
        assertEquals("(5.0*(-20.0))", testMul.print());
        assertEquals(-100, testMul.eval(""));

        testMul = new Mul(new Variable("x"), new Number(20));
        assertEquals("(x*20.0)", testMul.print());
        assertEquals(-600, testMul.eval("x = -30"));
        testExpr = testMul.derivative("x");
        assertEquals(20, testExpr.eval("x = 5"));
        assertEquals("((1.0*20.0)+(x*0.0))", testExpr.print());

        testMul = new Mul(new Variable("x"), new Variable("xyz"));
        assertEquals("(x*xyz)", testMul.print());
        assertEquals(150, testMul.eval("x = -30; xyz = -5"));
        testExpr = testMul.derivative("x");
        assertEquals(3, testExpr.eval("x = 5; xyz = 3"));
        assertEquals("((1.0*xyz)+(x*0.0))", testExpr.print());

    }
}