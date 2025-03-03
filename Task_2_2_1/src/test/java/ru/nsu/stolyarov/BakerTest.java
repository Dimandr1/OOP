package ru.nsu.stolyarov;

import org.junit.jupiter.api.Test;
import ru.nsu.stolyarov.interfaces.QueueTimedAddable;
import ru.nsu.stolyarov.interfaces.QueueTimedGettable;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BakerTest {
    int test;
    @Test
    void test() throws InterruptedException {
        Baker testBaker = new Baker(5);
        QueueTimedGettable blankGetter = new QueueTimedGettable() {
            @Override
            public int tryGet(long limit) {
                return ((int) limit);
            }
        };

        QueueTimedAddable blankAdder = new QueueTimedAddable() {
            @Override
            public boolean tryAdd(int element, long limit) {
                test = (int) element;
                return true;
            }
        };

        testBaker.cookNPut(blankGetter, blankAdder, 1);
        assertEquals(1, test);
        testBaker.cookNPut(blankGetter, blankAdder, 2);
        assertEquals(2, test);
        testBaker.cookNPut(blankGetter, blankAdder, -1);
        assertEquals(2, test);
    }



}