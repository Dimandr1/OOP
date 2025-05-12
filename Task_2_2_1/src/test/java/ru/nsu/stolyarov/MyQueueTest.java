package ru.nsu.stolyarov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class MyQueueTest {
    @Test
    void test() {
        MyQueue testQueue = new MyQueue(3);
        assertEquals(0, testQueue.len());
        testQueue.add(2);
        assertEquals(1, testQueue.len());
        testQueue.add(3);
        testQueue.add(4);
        assertEquals(3, testQueue.len());
        assertEquals(2, testQueue.extract());
        assertEquals(2, testQueue.len());
        testQueue.add(5);
        assertEquals(3, testQueue.len());
        assertEquals(3, testQueue.extract());
        assertEquals(4, testQueue.extract());
        assertEquals(1, testQueue.len());
        testQueue.add(6);
        assertEquals(2, testQueue.len());
        assertEquals(5, testQueue.extract());
        assertEquals(1, testQueue.len());


    }
}