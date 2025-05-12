package ru.nsu.stolyarov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import ru.nsu.stolyarov.interfaces.QueueTimedGettable;


class CarrierTest {
    @Test
    void test() {
        Carrier testCarrier = new Carrier(2);
        QueueTimedGettable blankGetter = new QueueTimedGettable() {
            @Override
            public int tryGet(long limit) {
                return ((int) limit);
            }
        };
        assertEquals(new ArrayList<Integer>(), testCarrier.takeAndDeliver(blankGetter, 0));
        ArrayList<Integer> testAr = new ArrayList<>();
        testAr.add(999);
        testAr.add(999);
        assertEquals(testAr, testCarrier.takeAndDeliver(blankGetter, 999));
    }
}