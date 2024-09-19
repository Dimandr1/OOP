package ru.nsu.stolyarov;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {
    @Test
    void test(){
        Card jack = new Card("jack", "spades");
        Card two = new Card("two", "spades");
        Card ace = new Card("ace", "spades");
        Hand testHand = new Hand();
        assertEquals(0, testHand.getTotalPoints());
        testHand.addCard(ace);
        assertEquals(11, testHand.getTotalPoints());
        testHand.addCard(jack);
        assertEquals(21, testHand.getTotalPoints());
        testHand.addCard(two);
        assertEquals(13, testHand.getTotalPoints());
    }
}