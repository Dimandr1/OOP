package ru.nsu.stolyarov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class HandTest {
    @Test
    void test() {

        //различные проверки корректности подсчёта очков, распечатывания руки

        Hand testHand = new Hand();
        assertEquals(0, testHand.getTotalPoints());
        Card ace = new Card("ace", "spades");
        testHand.addCard(ace);
        assertEquals(11, testHand.getTotalPoints());
        assertEquals("[ace of spades(11)] => 11", testHand.printHand());
        Card jack = new Card("jack", "spades");
        testHand.addCard(jack);
        assertEquals(21, testHand.getTotalPoints());
        Card two = new Card("two", "spades");
        testHand.addCard(two);
        assertEquals(13, testHand.getTotalPoints());
        Card five = new Card("five", "spades");
        assertEquals("[ace of spades(1), jack of spades(10), two of spades(2)] => 13",
                testHand.printHand());
        five.hidden = true;
        testHand.addCard(five);
        assertEquals(13, testHand.getTotalPoints());
        testHand.openHand();
        assertEquals(18, testHand.getTotalPoints());
    }
}