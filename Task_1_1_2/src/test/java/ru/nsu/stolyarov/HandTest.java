package ru.nsu.stolyarov;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HandTest {
    @Test
    void test() {
        Hand testHand = new Hand();
        assertEquals(0, testHand.getTotalPoints());
        Card ace = new Card("ace", "spades");
        testHand.addCard(ace);
        assertEquals(11, testHand.getTotalPoints());
        Card jack = new Card("jack", "spades");
        testHand.addCard(jack);
                    assertEquals(21, testHand.getTotalPoints());
                    Card two = new Card("two", "spades");
        testHand.addCard(two);
        assertEquals(13, testHand.getTotalPoints());
        Card five = new Card("five", "spades");
        five.hidden = true;
        testHand.addCard(five);
        assertEquals(13, testHand.getTotalPoints());
        testHand.openHand();
        assertEquals(18, testHand.getTotalPoints());
    }
}