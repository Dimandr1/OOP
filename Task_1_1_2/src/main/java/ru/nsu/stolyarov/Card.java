package ru.nsu.stolyarov;

public class Card {
    public String value;
    public String suit;
    public int points;
    public boolean hidden;

    /**
     * Инициализация новой карты.
     * @param value - значение карты (двойка, пятерка, туз и т.д.) текстом
     * @param suit - масть карты текстом
     */
    public Card(String value, String suit) {
        this.value = value;
        this.suit = suit;
        points = 0;
        this.hidden = false;
    }

    /**
     * печатает данные о карте.
     */
    public void printCard() {
        if (!hidden) {
            System.out.print(value + " of " + suit + "(" + points + ")");
        } else {
            System.out.print("<hidden card>");
        }
    }
}
