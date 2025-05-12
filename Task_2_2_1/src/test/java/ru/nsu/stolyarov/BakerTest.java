package ru.nsu.stolyarov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import ru.nsu.stolyarov.interfaces.QueueTimedAddable;
import ru.nsu.stolyarov.interfaces.QueueTimedGettable;


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

        testBaker.cookAndPut(blankGetter, blankAdder, 1);
        assertEquals(1, test);
        testBaker.cookAndPut(blankGetter, blankAdder, 2);
        assertEquals(2, test);
        testBaker.cookAndPut(blankGetter, blankAdder, -1);
        assertEquals(2, test);
    }


}