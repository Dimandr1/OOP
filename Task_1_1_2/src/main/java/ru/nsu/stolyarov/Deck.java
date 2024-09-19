package ru.nsu.stolyarov;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
    }

    /**
     * Очищает колоду и заполняет её несколькими колодами по 52 карты
     *
     * @param n - сколько колод по 52 карты нужно добавить
     */
    public void remakeDeck(int n) {
        cards.clear();
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < 4; i++) {
                String cur_suit = switch (i) {
                    case 0 -> "hearts";
                    case 1 -> "diamonds";
                    case 2 -> "spades";
                    default -> "clubs";
                };
                cards.add(new Card("two", cur_suit));
                cards.add(new Card("three", cur_suit));
                cards.add(new Card("four", cur_suit));
                cards.add(new Card("five", cur_suit));
                cards.add(new Card("six", cur_suit));
                cards.add(new Card("seven", cur_suit));
                cards.add(new Card("eight", cur_suit));
                cards.add(new Card("nine", cur_suit));
                cards.add(new Card("ten", cur_suit));
                cards.add(new Card("jack", cur_suit));
                cards.add(new Card("queen", cur_suit));
                cards.add(new Card("king", cur_suit));
                cards.add(new Card("ace", cur_suit));
            }
        }
        reshuffle();
    }

    /**
     * Перемещивает колоду
     */
    private void reshuffle() {
        Random rand = new Random();
        for (int i = cards.size() - 1; i > 0; i--) {
            int rand_int = rand.nextInt(i + 1);
            Card first = cards.get(rand_int), second = cards.get(i);
            cards.set(rand_int, second);
            cards.set(i, first);
        }
    }

    /**
     * @return - ссылка на верхнюю карту в колоде
     */
    public Card getTopCard() {
        if (cards.size() == 0) {
            return null;
        } else {
            Card return_card = cards.getLast();
            cards.removeLast();
            return return_card;
        }
    }
}
