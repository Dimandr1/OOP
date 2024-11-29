package ru.nsu.stolyarov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class ZachetkaFitTest {
    @Test
    void test() {
        ZachetkaFit zachetka = new ZachetkaFit(false);

        zachetka.setPass("Английский", 1, true);
        zachetka.setPass("История", 2, true);

        zachetka.setMark("аЛСД", 1, 3);
        zachetka.setMark("аЛСД", 2, 5);
        zachetka.setMark("История", 1, 5);
        zachetka.setMark("Дискретка", 1, 4);
        zachetka.setMark("Дискретка", 2, 3);

        assertEquals(4f, zachetka.getAverageMark());
        assertEquals(4.5, zachetka.getAverageDipMark());
        assertEquals(false, zachetka.mayStudyFree());
        assertEquals(false, zachetka.mayRecieveGrant());
        zachetka.setMark("Дискретка", 2, 4);
        assertEquals(true, zachetka.mayStudyFree());
        assertEquals(true, zachetka.mayRecieveGrant());
        assertEquals(true, zachetka.mayGetRed());
        zachetka.setMark("ИИ", 5, 4);
        zachetka.setMark("DL", 6, 4);
        zachetka.setMark("Инфобез", 7, 4);
        zachetka.setMark("Философия", 8, 4);
        assertEquals(false, zachetka.mayGetRed());


        zachetka.addZachet("***", "Зачёт", 5, false);
        zachetka.addDifZachet("ОБЖ", "Экзамен", 1);
    }
}