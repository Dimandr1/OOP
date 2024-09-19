package ru.nsu.stolyarov;

import org.junit.jupiter.api.Test;

class BlackjackTest {
    @Test
    void test() {
        Blackjack testBlackjack = new Blackjack();
        for (int i = 1; i <= 5; i++) {
            testBlackjack.startNextRound(i);
            testBlackjack.playerTakesCard();
            testBlackjack.dealersTurn();
            testBlackjack.endRound();
        }
    }
}