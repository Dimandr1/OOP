package ru.nsu.stolyarov;

/**
 * Общий абстрактный класс для всех выражений.
 */
public abstract class Expression {
    /**
     * Преобразование выражения в строковый вид.
     *
     * @return - выражение в виде строки
     */
    public abstract String print();

    /**
     * Взятие производной от выражения.
     *
     * @param var - переменная, по которой берется производная
     * @return - производная выражения
     */
    public abstract Expression derivative(String var);

    /**
     * Получить численное значение выражения.
     *
     * @param vals - означивание всех переменных
     * @return - значение выражения
     */
    public abstract int eval(String vals);
}
