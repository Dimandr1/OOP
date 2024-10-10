package ru.nsu.stolyarov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class ParserTest {
    @Test
    void test() {
        Parser parser = new Parser();
        Expression testExp;

        assertEquals("20.0", parser.parseExpression("20").print());
        assertEquals("(-5020.0)", parser.parseExpression("(-5020)").print());
        assertEquals("x", parser.parseExpression("x").print());
        assertEquals("AbObUs", parser.parseExpression("AbObUs").print());
        assertEquals("(2.0+4.0)", parser.parseExpression("(2+4)").print());
        assertEquals("((-123.0)+4.0)", parser.parseExpression("((-123)+4)").print());
        assertEquals("(2.0/x)", parser.parseExpression("(2/x)").print());
        assertEquals("(asd*4.0)", parser.parseExpression("(asd*4)").print());
        assertEquals("((-123.0)-(-5234.0))",
                parser.parseExpression("((-123)-(-5234))").print());
        assertEquals("((-123.1)-(-5234.1))",
                parser.parseExpression("((-123.1)-(-5234.1))").print());
    }
}