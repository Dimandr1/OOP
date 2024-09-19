package ru.nsu.stolyarov;

/**
 * Отдельная игральная карта с соответствующими параметрами.
 */
public class Card {
    public String value;
    public String suit;
    public int points;
    public boolean hidden;

    /**
     * Инициализация новой карты.
     *
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
     * Печатает данные о карте.
     */
    public String printCard() {
        if (!hidden) {
            return (value + " of " + suit + "(" + points + ")");
                } else {
            return ("<hidden card>");
        }
    }
}
