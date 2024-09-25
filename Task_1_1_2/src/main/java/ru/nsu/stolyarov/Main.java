package ru.nsu.stolyarov;

import java.util.Scanner;

/**
 * Blackjack.
 *
 * @author Dmitry Stolyarov
 */
public class Main {
    /**
     * Консольная игра в блэкджек.
     *
     * @param args - хотел бы удалить
     */
    public static void main(String[] args) {
        Blackjack casino = new Blackjack();
        casino.newGame();
    }
}