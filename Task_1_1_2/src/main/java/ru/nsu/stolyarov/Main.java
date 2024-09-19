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
        System.out.println("Welcome to blackjack!");
        Blackjack casino = new Blackjack();
        Scanner input = new Scanner(System.in);
        boolean continuePlaying = true;
        while (continuePlaying) {
            System.out.print("How many decks to use? [enter natural number]: ");
            int decks = input.nextInt();
            if (casino.startNextRound(decks)) {
                System.out.println("___________________\nYour turn.");
                System.out.print("Take another card? [y/n]: ");
                String takingInp = input.next();
                boolean taking = takingInp.equals("y");
                while (taking && casino.isPlaying()) {
                    casino.playerTakesCard();
                    System.out.print("Take another card? [y/n]: ");
                    takingInp = input.next();
                    taking = takingInp.equals("y");
                }

                casino.dealersTurn();

                casino.endRound();
            }

            System.out.print("Another round? [y/n]: ");
            String continuePlayingInput = input.next();
            continuePlaying = continuePlayingInput.equals("y");
        }
        System.out.println("Thank you for playing!");
    }
}