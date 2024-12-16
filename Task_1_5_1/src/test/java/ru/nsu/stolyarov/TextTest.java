package ru.nsu.stolyarov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TextTest {
    @Test
    void test() {
        String t;
        Text t2 = new Text("123");
        assertEquals("123", t2.toString());
        Text.Builder t1 = new Text.Builder();
        t = t1.build().toString();
        assertEquals("", t);
        t1.setText("asdfgd");
        t = t1.build().toString();
        assertEquals("asdfgd", t1.build().toString());
        t1.setBold(true);
        t = t1.build().toString();
        assertEquals("**asdfgd**", t1.build().toString());
        t1.setBold(false).setItalic(true);
        t = t1.build().toString();
        assertEquals("*asdfgd*", t1.build().toString());
        t1.setStrike(true).setText("s");
        t = t1.build().toString();
        assertEquals("*~~s~~*", t1.build().toString());
        t = t1.setItalic(false).setCode(true).build().toString();
        assertEquals("~~`s`~~", t1.build().toString());
        t = t1.setObject(t2).build().toString();
        assertEquals("123", t1.build().toString());

        t2 = new Text("32");
        Text t3 = new Text("56");
        assertEquals(false, t2.equals(t3));
        t3 = new Text("32");
        assertEquals(true, t2.equals(t3));

        t1 = new Text.Builder();
        Text.Builder t4 = new Text.Builder();
        t1.setText("32").setBold(true).setStrike(true);
        t4.setText("23").setBold(true);
        assertEquals(false, t1.build().equals(t4.build()));
        t4.setStrike(true);
        assertEquals(false, t1.build().equals(t4.build()));
        t4.setText("32");
        assertEquals(true, t1.build().equals(t4.build()));

    }
}