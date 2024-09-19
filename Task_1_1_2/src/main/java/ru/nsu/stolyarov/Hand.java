package ru.nsu.stolyarov;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> cards;
    private boolean acesLowCost;

    public Hand() {
        cards = new ArrayList<>();
        acesLowCost = false;
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
            /*ans = switch (card.value) {
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
            };*/
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
            ;
            if (card.value.equals("ace") && acesLowCost) {
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
            acesLowCost = true;
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
        int totalPoints = 0;
        for (int i = 0; i < cards.size(); i++) {
            totalPoints += cards.get(i).points;
        }
        return totalPoints;
    }

    /**
     * @param card - ссылка на карту, которую нужно добавить в руку
     */
    public void addCard(Card card) {
        card.points = checkCardPoints(card);
        cards.add(card);
        if (!acesLowCost) reCheckAces();
    }

    /**
     * удаляет карты из руки
     */
    public void clear() {
        cards.clear();
        acesLowCost = false;
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
