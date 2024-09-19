package ru.nsu.stolyarov;

import org.junit.jupiter.api.Test;


class MainTest {
    @Test
    void test() {
        Main testM = new Main();
        String[] empty = new String[0];
        testM.main(empty);
    }
}