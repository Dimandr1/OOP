package ru.nsu.stolyarov;

import java.util.Scanner;

/**
 * "Игра" в целом: раздача карт, подсчёт очков.
 */
public class Blackjack {
    private int round;
    private boolean roundGoes;
    protected Hand player;
    protected Hand dealer;
    private Deck deck;
    private Scanner input;
    public int playerScore;
    public int casinoScore;

    public Blackjack() {
        round = 0;
        roundGoes = false;
        player = new Hand();
        dealer = new Hand();
        deck = new Deck();
        input = new Scanner(System.in);
        playerScore = 0;
        casinoScore = 0;
    }

    /**
     * Инициализация параметров для тестирования.
     *
     * @param inputText - заданный заранее ввод
     * @param seed      - сид для фиксированного рандома
     */
    public Blackjack(String inputText, long seed) {
        round = 0;
        roundGoes = false;
        player = new Hand();
        dealer = new Hand();
        deck = new Deck(seed);
        input = new Scanner(inputText);
        playerScore = 0;
        casinoScore = 0;
    }

    /**
     * Начало новой игры.
     */
    public void newGame() {
        boolean continuePlaying = true;
        round = 0;
        playerScore = 0;
        casinoScore = 0;
        while (continuePlaying) {
            System.out.print("How many decks to use? [enter natural number]: ");
            int decks = input.nextInt();
            startNextRound(decks);
            if (roundGoes) {
                System.out.println("___________________\nYour turn.");
                System.out.print("Take another card? [y/n]: ");
                String takingInp = input.next();
                boolean taking = takingInp.equals("y");
                while (taking && roundGoes) {
                    playerTakesCard();
                    if (roundGoes) {
                        System.out.print("Take another card? [y/n]: ");
                        takingInp = input.next();
                        taking = takingInp.equals("y");
                    }
                }

                dealersTurn();

                endRound();
            }

            System.out.println("___________________\nCurrent score");
            System.out.println("Player - " + playerScore + " : " + casinoScore + " - Casino");

            System.out.print("Another round? [y/n]: ");
            String continuePlayingInput = input.next();
            continuePlaying = continuePlayingInput.equals("y");
        }
        System.out.println("Thank you for playing!");
    }

    /**
     * Собираем колоду, раздаем карты.
     *
     * @param decks - количество колод, которые мы замешиваем в игровую колоду
     */
    private void startNextRound(int decks) {
        if (roundGoes) {
            return;
        }

        roundGoes = true;
        round++;
        System.out.println("Round " + round);
        if (decks < 1) {
            decks = 1;
            System.out.println("There must be at least one deck.");
        }
        System.out.println("Using " + decks + " decks.");
        deck.remakeDeck(decks);
        deck.reshuffle();
        player.clear();
        dealer.clear();
        player.addCard(deck.getTopCard());
        player.addCard(deck.getTopCard());
        dealer.addCard(deck.getTopCard());
        Card tempCard = deck.getTopCard();
        dealer.addCard(tempCard);

        if (dealer.getTotalPoints() != 21 && player.getTotalPoints() != 21) {
            dealer.hideCard(1);
        }

        System.out.println("Dealer dealt the cards");

        System.out.print("Your cards: ");
        System.out.println(player.printHand());
        System.out.print("Dealer's cards: ");
        System.out.println(dealer.printHand());

        if (dealer.getTotalPoints() == 21 || player.getTotalPoints() == 21) {
            if (dealer.getTotalPoints() == 21) {
                System.out.println("The dealer has blackjack! Casino wins.");
                casinoScore++;
            } else {
                System.out.println("You have blackjack! You win.");
                playerScore++;
            }
            roundGoes = false;
        }
    }

    /**
     * Отдает игроку еще карту.
     */
    private void playerTakesCard() {
        if (!roundGoes) {
            return;
        }
        Card cardTaken = deck.getTopCard();
        player.addCard(cardTaken);
        System.out.print("You take a card: ");
        System.out.print(cardTaken.printCard());
        System.out.print("\n");
        System.out.print("Your cards: ");
        System.out.println(player.printHand());
        if (player.getTotalPoints() > 21) {
            roundGoes = false;
            System.out.println("You have too many points! Casino wins.");
            casinoScore++;
        }
    }

    /**
     * Дилер открывает карты и набирает себе до необходимого количества очков.
     */
    private void dealersTurn() {
        if (!roundGoes) {
            return;
        }

        System.out.println("___________________");
        dealer.openHand();
        System.out.println("The dealer opens his cards");
        System.out.println(dealer.printHand());
        if (dealer.getTotalPoints() < 17) {
            System.out.println("Dealer's turn.");
            while (dealer.getTotalPoints() < 17) {

                Card cardTaken = deck.getTopCard();
                dealer.addCard(cardTaken);
                System.out.print("The dealer takes a card: ");
                System.out.print(cardTaken.printCard());
                System.out.print("\n");
                System.out.print("Dealer's cards: ");
                System.out.println(dealer.printHand());
            }
        }
        System.out.println("___________________");
    }

    /**
     * Подсчёт очков, объявление победителя.
     */
    private void endRound() {
        if (roundGoes) {
            roundGoes = false;
            if (dealer.getTotalPoints() > 21) {
                System.out.println("The dealer has too many points! You win.");
                playerScore++;
            } else {
                if (dealer.getTotalPoints() >= player.getTotalPoints()) {
                    System.out.println("The dealer has more points or equal to you! "
                            + "Casino wins.");
                    casinoScore++;
                } else {
                    System.out.println("You have more points than the dealer! You win.");
                    playerScore++;

                }
            }
        }
    }
}
