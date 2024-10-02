package ru.nsu.stolyarov;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddTest {
    @Test
    void test(){
        Add testAdd = new Add(new Number(5), new Number(20));
        assertEquals("(5+20)", testAdd.print());
        assertEquals(25, testAdd.eval(""));
        Expression testExpr = testAdd.derivative("x");
        assertEquals(0, testExpr.eval(""));
        assertEquals("(0+0)", testExpr.print());

        testAdd = new Add(new Number(5), new Number(-20));
        assertEquals("(5+(-20))", testAdd.print());
        assertEquals(-15, testAdd.eval(""));
        testExpr = testAdd.derivative("x");
        assertEquals(0, testExpr.eval(""));
        assertEquals("(0+0)", testExpr.print());

        testAdd = new Add(new Number(-5), new Number(20));
        assertEquals("((-5)+20)", testAdd.print());
        assertEquals(15, testAdd.eval(""));
        testExpr = testAdd.derivative("x");
        assertEquals(0, testExpr.eval(""));
        assertEquals("(0+0)", testExpr.print());

        testAdd = new Add(new Variable("x"), new Number(20));
        assertEquals("(x+20)", testAdd.print());
        assertEquals(-10, testAdd.eval("x = -30"));
        testExpr = testAdd.derivative("x");
        assertEquals(1, testExpr.eval("x = 5"));
        assertEquals("(1+0)", testExpr.print());

        testAdd = new Add(new Variable("x"), new Variable("xyz"));
        assertEquals("(x+xyz)", testAdd.print());
        assertEquals(-10, testAdd.eval("x = -30; xyz = 20"));
        testExpr = testAdd.derivative("x");
        assertEquals(1, testExpr.eval("x = 5; xyz = 50"));
        assertEquals("(1+0)", testExpr.print());
    }
}