package ru.nsu.stolyarov;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> cards;
    private boolean aces_low_cost;

    public Hand() {
        cards = new ArrayList<>();
        aces_low_cost = false;
    }

    /**
     * Проверяет, сколько указанная карта дает очков данной руке
     *
     * @param card -- ссылка на карту, которую нужно проверить
     * @return - целочисленное значение очков карты
     */
    private int checkCardPoints(Card card) {
        int ans;
        if (card.hidden) {
            ans = 0;
        } else {
            ans = switch (card.value) {
                case "two" -> 2;
                case "three" -> 3;
                case "four" -> 4;
                case "five" -> 5;
                case "six" -> 6;
                case "seven" -> 7;
                case "eight" -> 8;
                case "nine" -> 9;
                case "ace" -> 11;
                default -> 10;
            };
            if (card.value.equals("ace") && aces_low_cost) {
                ans = 1;
            }
        }
        return ans;
    }

    /**
     * Тузы могут давать как 11 очков, так и 1, если суммарное количество очков превысило 21
     * Данная функция проверяет, не произошло ли это - и уменьшает очки за тузы, если да
     */
    private void reCheckAces() {
        int total_points = 0;
        for (int i = 0; i < cards.size(); i++) {
            total_points += cards.get(i).points;
        }
        if (total_points > 21) {
            aces_low_cost = true;
            for (int i = 0; i < cards.size(); i++) {
                if (cards.get(i).value.equals("ace") && !cards.get(i).hidden) {
                    cards.get(i).points = 1;
                }
            }
        }
    }

    /**
     * @return - возвращает суммарные очки за карты в руке
     */
    public int getTotalPoints() {
        int total_points = 0;
        for (int i = 0; i < cards.size(); i++) {
            total_points += cards.get(i).points;
        }
        return total_points;
    }

    /**
     * @param card - ссылка на карту, которую нужно добавить в руку
     */
    public void addCard(Card card) {
        card.points = checkCardPoints(card);
        cards.add(card);
        if (!aces_low_cost) reCheckAces();
    }

    /**
     * удаляет карты из руки
     */
    public void clear() {
        cards.clear();
        aces_low_cost = false;
    }

    /**
     * печатает все карты в руке и сумму очков
     */
    public void printHand() {
        System.out.print("[");
        for (int i = 0; i < cards.size(); i++) {
            cards.get(i).printCard();
            if (i != cards.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("] => " + getTotalPoints());
    }

    /**
     * все скрытые карты в руке делает открытыми                
     */
    public void openHand() {
        for (int i = 0; i < cards.size(); i++) {
            cards.get(i).hidden = false;
            cards.get(i).points = checkCardPoints(cards.get(i));
        }
        reCheckAces();
    }
}
