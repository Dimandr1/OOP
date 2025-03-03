package ru.nsu.stolyarov;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SafeQueueManagerTest {
    @Test
    void test() throws InterruptedException {
        SafeQueueManager testQueue = new SafeQueueManager(3);
        assertEquals(0, testQueue.len());
        assertEquals(-1, testQueue.tryGet(100));
        assertEquals(true, testQueue.tryAdd(2, 0));
        assertEquals(1, testQueue.len());
        assertEquals(true, testQueue.tryAdd(3, 100));
        assertEquals(true, testQueue.tryAdd(4, 50));
        assertEquals(3, testQueue.len());
        assertEquals(false, testQueue.tryAdd(5, 50));
        assertEquals(3, testQueue.len());
        assertEquals(2, testQueue.tryGet(100));
        assertEquals(2, testQueue.len());
        assertEquals(true, testQueue.tryAdd(5, 0));
        assertEquals(3, testQueue.len());
        assertEquals(3, testQueue.tryGet(0));
        assertEquals(4, testQueue.tryGet(100));
        assertEquals(1, testQueue.len());
        assertEquals(true, testQueue.tryAdd(6, 50));
        assertEquals(2, testQueue.len());
        assertEquals(5, testQueue.tryGet(100));
        assertEquals(1, testQueue.len());
        testQueue.tryGet(0);
        Thread first = new Thread(() -> testQueue.tryAdd(2, 0));
        first.start();
        first.join();
        assertEquals(1, testQueue.len());
        assertEquals(2, testQueue.tryGet(100));
        first = new Thread(() -> testQueue.tryAdd(2, 0));
        first.start();
        first.join();
        first = new Thread(() -> testQueue.tryAdd(2, 0));
        first.start();
        first.join();
        first = new Thread(() -> testQueue.tryAdd(2, 0));
        first.start();
        first.join();
        assertEquals(3, testQueue.len());
        first = new Thread(() -> testQueue.tryAdd(2, 0));
        first.start();
        Thread second = new Thread(() -> testQueue.tryGet(0));
        Thread third = new Thread(() -> testQueue.tryGet(100));
        third.start();
        third.join();
        first.join();
        assertEquals(3, testQueue.len());
        second.start();
        second.join();
        assertEquals(2, testQueue.len());
        testQueue.tryGet(0);
        testQueue.tryGet(0);
        assertEquals(0, testQueue.len());
        second = new Thread(() -> testQueue.tryGet(0));
        second.start();
        first = new Thread(() -> testQueue.tryAdd(2, 0));
        first.start();
        first.join();
        second.join();
        assertEquals(0, testQueue.len());


    }
}