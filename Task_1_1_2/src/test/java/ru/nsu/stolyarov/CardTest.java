package ru.nsu.stolyarov;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class CardTest {
    @Test
    void test() {
        Card ace = new Card("ace", "spades");
        ace.points = 11;
        assertEquals("ace of spades(11)", ace.printCard());
        Card two = new Card("two", "spades");
        two.hidden = true;
        two.points = 0;
        assertEquals("<hidden card>", ace.printCard());
    }
}