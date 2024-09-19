package ru.nsu.stolyarov;

import java.util.Scanner;

/**
 * Blackjack.
 *
 * @author Dmitry Stolyarov
 */
public class Main {
    /**
     * Консольная игра в блэкджек
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Welcome to Blackjack!");
        Scanner scan = new Scanner(System.in);
        boolean cont = true;
        int k = 1;
        Hand player = new Hand();
        Hand kazinich = new Hand();
        Deck deck = new Deck();
        while (cont) {
            System.out.println("Round " + k);
            System.out.print("How many decks to use? [enter natural number]: ");
            int decks = scan.nextInt();
            if (decks < 1) {
                decks = 1;
                System.out.println("There must be at least one deck.");
            }
            deck.remakeDeck(decks);
            player.clear();
            kazinich.clear();
            player.addCard(deck.getTopCard());
            player.addCard(deck.getTopCard());
            kazinich.addCard(deck.getTopCard());
            Card tempCard = deck.getTopCard();
            tempCard.hidden = true;
            kazinich.addCard(tempCard);

            System.out.println("Dealer dealt the cards");

            System.out.print("Your cards: ");
            player.printHand();
            System.out.print("Dealer's cards: ");
            kazinich.printHand();

            int playerPoints = player.getTotalPoints();
            int dealerPoints = kazinich.getTotalPoints();
            if (dealerPoints == 21 || playerPoints == 21) {
                kazinich.openHand();
                System.out.println("The dealer opens his cards");
                kazinich.printHand();
                if (dealerPoints == 21) {
                    System.out.println("The dealer has blackjack! Casino wins.");
                } else {
                    System.out.println("You have blackjack! You win.");
                }
            } else {
                System.out.println("___________________\nYour turn.");
                boolean taking;
                System.out.print("Take another card? [y/n]: ");
                String takeInp;
                takeInp = scan.next();
                taking = takeInp.equals("y");
                while (taking) {

                    Card cardTaken = deck.getTopCard();
                    player.addCard(cardTaken);
                    System.out.print("You take a card: ");
                    cardTaken.printCard();
                    System.out.print("\n");
                    System.out.print("Your cards: ");
                    player.printHand();

                    if (player.getTotalPoints() < 21) {
                        System.out.print("Take another card? [y/n]: ");
                        takeInp = scan.next();
                        taking = takeInp.equals("y");
                    } else {
                        taking = false;
                    }
                }

                if (player.getTotalPoints() > 21) {
                    System.out.println("You have too many points! Casino wins.");
                } else {
                    if (kazinich.getTotalPoints() < 17) {
                        System.out.println("___________________\nDealer's turn.");
                        kazinich.openHand();
                        System.out.println("The dealer opens his cards");
                        kazinich.printHand();
                        while (kazinich.getTotalPoints() < 17) {

                            Card card_taken = deck.getTopCard();
                            kazinich.addCard(card_taken);
                            System.out.print("The dealer takes a card: ");
                            card_taken.printCard();
                            System.out.print("\n");

                            System.out.print("Dealer's cards: ");
                            kazinich.printHand();
                        }
                        System.out.println("___________________");
                    }
                    playerPoints = player.getTotalPoints();
                    dealerPoints = kazinich.getTotalPoints();
                    if (dealerPoints > 21) {
                        System.out.println("The dealer has too many points! You win.");
                    } else {
                        if (dealerPoints >= playerPoints) {
                            System.out.println("The dealer has more points or equal to you! " +
                                    "Casino wins.");
                        } else {
                            System.out.println("You have more points than the dealer! You win.");

                        }
                    }
                }
            }
            System.out.print("Another round? [y/n]: ");
            String roundInp = scan.next();
            cont = roundInp.equals("y");

            k++;
        }
        System.out.println("Thank you for playing!");
    }
}