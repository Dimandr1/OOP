package ru.nsu.stolyarov;

import java.util.ArrayList;
import java.util.Random;

/**
 * Стопка карт из некоторого количества колод.
 */
public class Deck {
    private ArrayList<Card> cards;
    private Random rand;

    /**
     * Инициализация параметров.
     */
    public Deck() {
        cards = new ArrayList<>();
        rand = new Random();
    }

    /**
     * Инициализация параметров.
     *
     * @param seed - задание сида для рандома
     */
    public Deck(long seed) {
        cards = new ArrayList<>();
        rand = new Random();
        rand.setSeed(seed);
    }

    /**
     * Очищает колоду и заполняет её несколькими колодами по 52 карты.
     *
     * @param n - сколько колод по 52 карты нужно добавить
     */
    public void remakeDeck(int n) {
        cards.clear();
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < 4; i++) {
                String curSuit;
                switch (i) {
                    case 0:
                        curSuit = "hearts";
                        break;
                    case 1:
                        curSuit = "diamonds";
                        break;
                    case 2:
                        curSuit = "spades";
                        break;
                    default:
                        curSuit = "clubs";
                        break;
                }
                cards.add(new Card("two", curSuit));
                cards.add(new Card("three", curSuit));
                cards.add(new Card("four", curSuit));
                cards.add(new Card("five", curSuit));
                cards.add(new Card("six", curSuit));
                cards.add(new Card("seven", curSuit));
                cards.add(new Card("eight", curSuit));
                cards.add(new Card("nine", curSuit));
                cards.add(new Card("ten", curSuit));
                cards.add(new Card("jack", curSuit));
                cards.add(new Card("queen", curSuit));
                cards.add(new Card("king", curSuit));
                cards.add(new Card("ace", curSuit));
            }
        }
    }

    /**
     * Перемешивает колоду.
     */
    public void reshuffle() {
        for (int i = cards.size() - 1; i > 0; i--) {
            int randInt = rand.nextInt(i + 1);
            Card first = cards.get(randInt);
            Card second = cards.get(i);
            cards.set(randInt, second);
            cards.set(i, first);
        }
    }

    /**
     * Возвращает ссылку на верхнюю карту в колоде.
     *
     * @return - ссылка на верхнюю карту в колоде
     */
    public Card getTopCard() {
        if (cards.size() == 0) {
            return null;
        } else {
            Card returnCard = cards.get(cards.size() - 1);
            cards.remove(cards.size() - 1);
            return returnCard;
        }
    }

    /**
     * Возвращает карту под указанным индексом. Используется только для тестирования.
     *
     * @param i - индекс карты, которую хотим посмотреть
     * @return - возвращает требуемую карту
     */
    public Card lookAtCard(int i) {
        if (i >= cards.size()) {
            return null;
        }
        return cards.get(i);
    }

}
