package ru.nsu.stolyarov;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderManagerTest {
    @Test
    void test() {
        OrderManager testOrderQueue = new OrderManager(3);
        assertEquals(0, testOrderQueue.len());
        assertEquals(0, testOrderQueue.takeOrder());
        assertEquals(1, testOrderQueue.len());
        assertEquals(1, testOrderQueue.takeOrder());
        assertEquals(0, testOrderQueue.tryGet(0));
        assertEquals(1, testOrderQueue.len());
        assertEquals(2, testOrderQueue.takeOrder());
        assertEquals(3, testOrderQueue.takeOrder());
        assertEquals(3, testOrderQueue.len());
    }
}