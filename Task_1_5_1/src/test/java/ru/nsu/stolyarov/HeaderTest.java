package ru.nsu.stolyarov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import javax.management.InvalidAttributeValueException;

class HeaderTest {
    @Test
    void test() {
        Header t3 = new Header();
        String t = t3.toString();
        assertEquals("# ", t);
        Header.Builder t1 = new Header.Builder();
        t = t1.build().toString();
        assertEquals("# ", t);
        t = t1.setText("asd").build().toString();
        assertEquals("# asd", t);
        try {
            t = t1.setSize(4).build().toString();
        } catch (InvalidAttributeValueException e) {
            throw new RuntimeException(e);
        }
        assertEquals("#### asd", t);

        t = t1.setObject(t3).build().toString();
        assertEquals("# ", t);
    }
}