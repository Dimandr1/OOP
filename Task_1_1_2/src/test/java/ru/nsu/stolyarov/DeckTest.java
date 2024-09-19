package ru.nsu.stolyarov;

import org.junit.jupiter.api.Test;

class DeckTest {
    @Test
    void test() {
        Deck testDeck = new Deck();
        testDeck.remakeDeck(1);
        testDeck.remakeDeck(5);
        testDeck.remakeDeck(10);
        testDeck.getTopCard();
    }
}