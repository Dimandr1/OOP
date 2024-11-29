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

        addZachet("Английский", "Зачёт", 1);
        addZachet("История", "Зачёт", 2);
        addZachet("ФКиС", "Зачёт", 3);
        addZachet("Откис", "Зачёт", 4, false);
        addZachet("Прокис", "Зачёт", 5, false);
        addZachet("Инфобез", "Зачёт", 6);
        addZachet("ОБЖ", "Зачёт", 8);

        addDifZachet("аЛСД", "Дифзачёт", 1, false);
        addDifZachet("аЛСД", "Дифзачёт", 2, false);
        addDifZachet("История", "Дифзачёт", 1);
        addDifZachet("Дискретка", "Экзамен", 1);
        addDifZachet("Дискретка", "Экзамен", 2);
        addDifZachet("ООП", "Дифзачёт", 3);
        addDifZachet("ООП", "Экзамен", 4);
        addDifZachet("Оси", "Дифзачёт", 3);
        addDifZachet("Оси", "Экзамен", 3);
        addDifZachet("ИИ", "Экзамен", 3);
        addDifZachet("Английский", "Дифзачёт", 5);
        addDifZachet("ИИ", "Экзамен", 5);
        addDifZachet("DL", "Дифзачёт", 6);
        addDifZachet("Инфобез", "Дифзачёт", 7);
        addDifZachet("Философия", "Дифзачёт", 8);
        addDifZachet("ВКР", "Защита ВКР", 8);
    }
}
