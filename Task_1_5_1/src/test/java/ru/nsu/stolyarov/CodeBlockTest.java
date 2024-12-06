package ru.nsu.stolyarov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


class CodeBlockTest {
    @Test
    void test() {
        CodeBlock t3 = new CodeBlock();
        String t = t3.toString();
        assertEquals("``````", t);
        CodeBlock.Builder t1 = new CodeBlock.Builder();
        t = t1.build().toString();
        assertEquals("``````", t);
        t = t1.setCode("adsgfsdfg").build().toString();
        assertEquals("```adsgfsdfg```", t);
    }
}