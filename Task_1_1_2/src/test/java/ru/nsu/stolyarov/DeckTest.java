package ru.nsu.stolyarov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.Random;

class DeckTest {
    @Test
    void test() {

        //проверка корректности создания колоды

        Deck testDeck = new Deck();
        Card testCard;
        testDeck.remakeDeck(1);
        testCard = testDeck.lookAtCard(52);
        assertEquals(null, testCard);
        testCard = testDeck.lookAtCard(0);
        assertEquals("two", testCard.value);
        assertEquals("hearts", testCard.suit);
        testCard = testDeck.lookAtCard(12);
        assertEquals("ace", testCard.value);
        assertEquals("hearts", testCard.suit);
        testCard = testDeck.lookAtCard(29);
        assertEquals("five", testCard.value);
        assertEquals("spades", testCard.suit);

        //проверка корректности взятия верхней карты

        Card anotherTestCard = testDeck.lookAtCard(51);
        testCard = testDeck.getTopCard();
        assertEquals(anotherTestCard, testCard);

        testCard = testDeck.lookAtCard(51);
        assertEquals(null, testCard);

        //проверки правильности размера колоды после создания
        testDeck.remakeDeck(3);
        testCard = testDeck.lookAtCard(156);
        assertEquals(null, testCard);
        testCard = testDeck.lookAtCard(155);
        assertEquals("ace", testCard.value);
        assertEquals("clubs", testCard.suit);

        //проверки тасования колоды при фиксированном рандоме
        testDeck.remakeDeck(1);

        long seed = 123;
        Deck testDeckSecond = new Deck(seed);
        testDeckSecond.remakeDeck(1);
        testDeckSecond.reshuffle();
        Random rand = new Random();
        rand.setSeed(seed);
        Card randCard = testDeck.lookAtCard(rand.nextInt(52));
        Card topCard = testDeckSecond.getTopCard();
        assertEquals(randCard.value, topCard.value);
        assertEquals(randCard.suit, topCard.suit);
        randCard = testDeck.lookAtCard(rand.nextInt(51));
        topCard = testDeckSecond.getTopCard();
        assertEquals(randCard.value, topCard.value);
        assertEquals(randCard.suit, topCard.suit);

        seed = 53245;
        testDeckSecond = new Deck(seed);
        testDeckSecond.remakeDeck(1);
        testDeckSecond.reshuffle();
        rand = new Random();
        rand.setSeed(seed);
        randCard = testDeck.lookAtCard(rand.nextInt(52));
        topCard = testDeckSecond.getTopCard();
        assertEquals(randCard.value, topCard.value);
        assertEquals(randCard.suit, topCard.suit);
        randCard = testDeck.lookAtCard(rand.nextInt(51));
        topCard = testDeckSecond.getTopCard();
        assertEquals(randCard.value, topCard.value);
        assertEquals(randCard.suit, topCard.suit);

        seed = 1;
        testDeckSecond = new Deck(seed);
        testDeckSecond.remakeDeck(1);
        testDeckSecond.reshuffle();
        for (int i = 0; i < 10; i++) {
            topCard = testDeckSecond.getTopCard();
            System.out.print(topCard.printCard() + ", ");
        }


    }
}