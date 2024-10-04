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
     * @param var - имя переменной, по которой берется производная
     * @return - производная выражения
     */
    public abstract Expression derivative(String var);

    /**
     * Получить численное значение выражения.
     *
     * @param vals - строка в виде "var1 = val1; var2 = val2; ...; varN = valN",
     *             где varI - имя переменной, а valI - её целочисленное значение.       
     * @return - значение выражения
     */
    public abstract int eval(String vals);
}
