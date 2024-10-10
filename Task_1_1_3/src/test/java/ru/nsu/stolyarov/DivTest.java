package ru.nsu.stolyarov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class DivTest {
    @Test
    void test() {
        Div testDiv = new Div(new Number(20), new Number(5));
        assertEquals("(20.0/5.0)", testDiv.print());
        assertEquals(4, testDiv.eval(""));
        Expression testExpr = testDiv.derivative("x");
        assertEquals(0, testExpr.eval(""));
        assertEquals("(((0.0*5.0)-(20.0*0.0))/(5.0*5.0))", testExpr.print());

        testDiv = new Div(new Number(-20), new Number(5));
        assertEquals("((-20.0)/5.0)", testDiv.print());
        assertEquals(-4, testDiv.eval(""));

        testDiv = new Div(new Variable("x"), new Number(20));
        assertEquals("(x/20.0)", testDiv.print());
        assertEquals(25, testDiv.eval("x = 500"));

        testDiv = new Div(new Variable("x"), new Number(-1));
        testExpr = testDiv.derivative("x");
        assertEquals(-1, testExpr.eval("x = 5"));
        assertEquals("(((1.0*(-1.0))-(x*0.0))/((-1.0)*(-1.0)))", testExpr.print());


        testDiv = new Div(new Variable("x"), new Variable("xyz"));
        assertEquals("(x/xyz)", testDiv.print());
        assertEquals(150, testDiv.eval("x = -450; xyz = -3"));
        testExpr = testDiv.derivative("x");
        assertEquals(-1, testExpr.eval("x = 5; xyz = -1"));
        assertEquals("(((1.0*xyz)-(x*0.0))/(xyz*xyz))", testExpr.print());
    }
}