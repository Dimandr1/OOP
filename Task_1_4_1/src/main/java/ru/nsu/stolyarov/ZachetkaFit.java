package ru.nsu.stolyarov;

/**
 * Частный случай зачётки (ФИТ).
 */
public class ZachetkaFit extends Zachetka {
    /**
     * Инициализация зачётки с генерацией дисциплин ФИТа.
     *
     * @param studyFree на бюджете ли студент
     */
    public ZachetkaFit(boolean studyFree) {
        super(studyFree);

        AddZachet("Английский", "Зачёт", 1);
        AddZachet("История", "Зачёт", 2);
        AddZachet("ФКиС", "Зачёт", 3);
        AddZachet("Откис", "Зачёт", 4, false);
        AddZachet("Прокис", "Зачёт", 5, false);
        AddZachet("Инфобез", "Зачёт", 6);
        AddZachet("ОБЖ", "Зачёт", 8);

        AddDifZachet("аЛСД", "Дифзачёт", 1, false);
        AddDifZachet("аЛСД", "Дифзачёт", 2, false);
        AddDifZachet("История", "Дифзачёт", 1);
        AddDifZachet("Дискретка", "Экзамен", 1);
        AddDifZachet("Дискретка", "Экзамен", 2);
        AddDifZachet("ООП", "Дифзачёт", 3);
        AddDifZachet("ООП", "Экзамен", 4);
        AddDifZachet("Оси", "Дифзачёт", 3);
        AddDifZachet("Оси", "Экзамен", 3);
        AddDifZachet("ИИ", "Экзамен", 3);
        AddDifZachet("Английский", "Дифзачёт", 5);
        AddDifZachet("ИИ", "Экзамен", 5);
        AddDifZachet("DL", "Дифзачёт", 6);
        AddDifZachet("Инфобез", "Дифзачёт", 7);
        AddDifZachet("Философия", "Дифзачёт", 8);
        AddDifZachet("ВКР", "Защита ВКР", 8);
    }
}
