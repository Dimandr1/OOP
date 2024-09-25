package ru.nsu.stolyarov;

import java.util.ArrayList;

/**
 * Карты в руке игрока.
 */
public class Hand {
    private ArrayList<Card> cards;
    private boolean acesLowCost;

    public Hand() {
        cards = new ArrayList<>();
        acesLowCost = false;
    }

    /**
     * Проверяет, сколько указанная карта дает очков данной руке.
     *
     * @param card -- ссылка на карту, которую нужно проверить
     * @return - целочисленное значение очков карты
     */
    private int checkCardPoints(Card card) {
        int ans;
        switch (card.value) {
            case "two":
                ans = 2;
                break;
            case "three":
                ans = 3;
                break;
            case "four":
                ans = 4;
                break;
            case "five":
                ans = 5;
                break;
            case "six":
                ans = 6;
                break;
            case "seven":
                ans = 7;
                break;
            case "eight":
                ans = 8;
                break;
            case "nine":
                ans = 9;
                break;
            case "ace":
                ans = 11;
                break;
            default:
                ans = 10;
                break;
        }
        if (card.value.equals("ace") && acesLowCost) {
            ans = 1;
        }
        return ans;
    }

    /**
     * Тузы могут давать как 11 очков, так и 1, если суммарное количество очков превысило 21.
     * Данная функция проверяет, не произошло ли это - и уменьшает очки за тузы, если да
     */
    private void reCheckAces() {
        int totalPoints = 0;
        for (int i = 0; i < cards.size(); i++) {
            totalPoints += cards.get(i).points;
        }
        if (totalPoints > 21) {
            acesLowCost = true;
            for (int i = 0; i < cards.size(); i++) {
                if (cards.get(i).value.equals("ace") && !cards.get(i).hidden) {
                    cards.get(i).points = 1;
                }
            }
        }
    }

    /**
     * Возвращает суммарные очки за карты в руке.
     *
     * @return - возвращает суммарные очки за карты в руке
     */
    public int getTotalPoints() {
        int totalPoints = 0;
        for (int i = 0; i < cards.size(); i++) {
            if (!cards.get(i).hidden) {
                totalPoints += cards.get(i).points;
            }
        }
        return totalPoints;
    }

    /**
     * Добавляет в руку игрока новую карту.
     *
     * @param card - ссылка на карту, которую нужно добавить в руку
     */
    public void addCard(Card card) {
        card.points = checkCardPoints(card);
        cards.add(card);
        if (!acesLowCost) {
            reCheckAces();
        }
    }

    /**
     * Удаляет все карты из руки.
     */
    public void clear() {
        cards.clear();
        acesLowCost = false;
    }

    /**
     * Печатает все карты в руке и сумму очков.
     */
    public String printHand() {
        String ans = "";
        ans += "[";
        for (int i = 0; i < cards.size(); i++) {
            ans += cards.get(i).printCard();
            if (i != cards.size() - 1) {
                ans += ", ";
            }
        }
        ans += "] => " + getTotalPoints();
        return ans;
    }

    /**
     * Все скрытые карты в руке делает открытыми.
     */
    public void openHand() {
        for (int i = 0; i < cards.size(); i++) {
            cards.get(i).hidden = false;
            cards.get(i).points = checkCardPoints(cards.get(i));
        }
        reCheckAces();
    }

    /**
     * Скрыть указанную карту
     *
     * @param i - индекс карты, которую нужно скрыть
     */
    public void hideCard(int i) {
        cards.get(i).hidden = true;
    }
}
