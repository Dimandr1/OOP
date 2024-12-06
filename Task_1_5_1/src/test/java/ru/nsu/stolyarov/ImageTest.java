package ru.nsu.stolyarov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


class ImageTest {
    @Test
    void test() {
        Image t2 = new Image();
        String t = t2.toString();
        assertEquals("![]()", t);
        Image.Builder t1 = new Image.Builder();
        t = t1.build().toString();
        assertEquals("![]()", t);
        t = t1.setUrl("fds").build().toString();
        assertEquals("![](fds)", t);
        Text t3 = new Text("123");
        t = t1.setTextObj(t3).build().toString();
        assertEquals("![123](fds)", t);
        t = t1.setBold(true).setItalic(true).build().toString();
        assertEquals("***![123](fds)***", t);
        t = t1.setBold(false).setStrike(true).setCode(true).build().toString();
        assertEquals("*~~`![123](fds)`~~*", t);
        t = t1.setObject(t2).setText("sd").setItalic(true).build().toString();
        assertEquals("*![sd]()*", t);
    }
}