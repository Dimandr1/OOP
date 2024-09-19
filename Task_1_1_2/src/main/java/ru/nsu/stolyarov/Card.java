package ru.nsu.stolyarov;

public class Card {
    public String value, suit;
    public int points;
    public boolean hidden;

    public Card(String value, String suit) {
        this.value = value;
        this.suit = suit;
        points = 0;
        this.hidden = false;
    }

    /**
     * печатает данные о карте
     */
    public void printCard() {
        if (!hidden) {
            System.out.print(value + " of " + suit + "(" + points + ")");
        } else {
            System.out.print("<hidden card>");
        }
    }
}
