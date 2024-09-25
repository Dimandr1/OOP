package ru.nsu.stolyarov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class BlackjackTest {
    @Test
    void test() {
        //проверяем, что игра не крашится при адекватном вводе
        //и что исход получается правильный
        long seed = 0;
        String input = "1\ny\nn\nn";
        Blackjack testBlackjack = new Blackjack(input, seed);
        testBlackjack.newGame();
        assertEquals(1, testBlackjack.playerScore);
        assertEquals(0, testBlackjack.casinoScore);

        seed = 1;
        input = "1\ny\nn\nn";
        testBlackjack = new Blackjack(input, seed);
        testBlackjack.newGame();
        assertEquals(0, testBlackjack.playerScore);
        assertEquals(1, testBlackjack.casinoScore);
    }
}