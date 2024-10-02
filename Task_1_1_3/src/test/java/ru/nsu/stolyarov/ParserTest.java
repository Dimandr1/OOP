package ru.nsu.stolyarov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class ParserTest {
    @Test
    void test() {
        Parser parser = new Parser();
        Expression testExp;

        assertEquals("20", parser.parseExpression("20").print());
        assertEquals("(-5020)", parser.parseExpression("(-5020)").print());
        assertEquals("x", parser.parseExpression("x").print());
        assertEquals("AbObUs", parser.parseExpression("AbObUs").print());
        assertEquals("(2+4)", parser.parseExpression("(2+4)").print());
        assertEquals("((-123)+4)", parser.parseExpression("((-123)+4)").print());
        assertEquals("(2/x)", parser.parseExpression("(2/x)").print());
        assertEquals("(asd*4)", parser.parseExpression("(asd*4)").print());
        assertEquals("((-123)-(-5234))",
                parser.parseExpression("((-123)-(-5234))").print());
    }
}