package ru.nsu.stolyarov;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class ZachetkaFITTest {
    @Test
    void test() {
        ZachetkaFIT zachetka = new ZachetkaFIT(false);

        zachetka.SetPass("Английский", 1, true);
        zachetka.SetPass("История", 2, true);

        zachetka.SetMark("аЛСД", 1, 3);
        zachetka.SetMark("аЛСД", 2, 5);
        zachetka.SetMark("История", 1, 5);
        zachetka.SetMark("Дискретка", 1, 4);
        zachetka.SetMark("Дискретка", 2, 3);

        assertEquals(4f, zachetka.GetAverageMark());
        assertEquals(4.5, zachetka.GetAverageDipMark());
        assertEquals(false, zachetka.MayStudyFree());
        assertEquals(false, zachetka.MayRecieveGrant());
        zachetka.SetMark("Дискретка", 2, 4);
        assertEquals(true, zachetka.MayStudyFree());
        assertEquals(true, zachetka.MayRecieveGrant());
        assertEquals(true, zachetka.MayGetRed());
        zachetka.SetMark("ИИ", 5, 4);
        zachetka.SetMark("DL", 6, 4);
        zachetka.SetMark("Инфобез", 7, 4);
        zachetka.SetMark("Философия", 8, 4);
        assertEquals(false, zachetka.MayGetRed());


        zachetka.AddZachet("***", "Зачёт", 5, false);
        zachetka.AddDifZachet("ОБЖ", "Экзамен", 1);
    }
}