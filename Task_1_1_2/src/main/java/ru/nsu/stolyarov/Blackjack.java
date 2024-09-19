package ru.nsu.stolyarov;

/**
 * "Игра" в целом: раздача карт, подсчёт очков.
 */
public class Blackjack {
    private int round;
    private boolean playing;
    private Hand player;
    private Hand dealer;
    private Deck deck;

    public Blackjack() {
        round = 0;
        playing = false;
        player = new Hand();
        dealer = new Hand();
        deck = new Deck();
    }

    /**
     * Собираем колоду, раздаем карты.
     *
     * @param decks - количество колод, которые мы замешиваем в игровую колоду
     * @return - возвращает, продолжается ли игра
     */
    public boolean startNextRound(int decks) {
        if (playing) {
            return true;
        }

        playing = true;
        round++;
        System.out.println("Round " + round);
        if (decks < 1) {
            decks = 1;
            System.out.println("There must be at least one deck.");
        }
        System.out.println("Using " + decks + " decks.");
        deck.remakeDeck(decks);
        player.clear();
        dealer.clear();
        player.addCard(deck.getTopCard());
        player.addCard(deck.getTopCard());
        dealer.addCard(deck.getTopCard());
        Card tempCard = deck.getTopCard();
        tempCard.hidden = true;
        dealer.addCard(tempCard);

        System.out.println("Dealer dealt the cards");

        System.out.print("Your cards: ");
        System.out.println(player.printHand());
        System.out.print("Dealer's cards: ");
        System.out.println(dealer.printHand());

        if (dealer.getTotalPoints() == 21 || player.getTotalPoints() == 21) {
            dealer.openHand();
            System.out.println("The dealer opens his cards");
            System.out.println(dealer.printHand());
            if (dealer.getTotalPoints() == 21) {
                System.out.println("The dealer has blackjack! Casino wins.");
            } else {
                System.out.println("You have blackjack! You win.");
            }
            playing = false;
        }

        return playing;
    }

    /**
     * Доступ к информации, продолжается ли игра.
     *
     * @return - продолжается ли игра
     */
    public boolean isPlaying() {
        return playing;
    }

    /**
     * Отдает игроку еще карту.
     *
     * @return - возвращает, продолжается ли игра
     */
    public boolean playerTakesCard() {
        if (!playing) {
            return false;
        }
        Card cardTaken = deck.getTopCard();
        player.addCard(cardTaken);
        System.out.print("You take a card: ");
        System.out.print(cardTaken.printCard());
        System.out.print("\n");
        System.out.print("Your cards: ");
        System.out.println(player.printHand());
        if (player.getTotalPoints() > 21) {
            playing = false;
            System.out.println("You have too many points! Casino wins.");
        }
        return playing;
    }

    /**
     * Дилер открывает карты набирает себе до необходимого количества очков.
     *
     * @return - возвращает, продолжается ли игра
     */
    public boolean dealersTurn() {
        if (!playing) {
            return false;
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

        return playing;
    }

    /**
     * Подсчёт очков, объявление победителя.
     */
    public void endRound() {
        if (playing) {
            playing = false;
            if (dealer.getTotalPoints() > 21) {
                System.out.println("The dealer has too many points! You win.");
            } else {
                if (dealer.getTotalPoints() >= player.getTotalPoints()) {
                    System.out.println("The dealer has more points or equal to you! "
                            + "Casino wins.");
                } else {
                    System.out.println("You have more points than the dealer! You win.");

                }
            }
        }
    }
}
