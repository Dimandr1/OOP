package ru.nsu.stolyarov;

/**
 * Константа.
 */
public class Number extends Expression {
    private double value;

    /**
     * Инициализация константы.
     *
     * @param value - значение константы
     */
    public Number(double value) {
        this.value = value;
    }

    public String print() {
        if (value >= 0) {
            return "" + value;
        } else {
            return "(" + value + ")";
        }
    }

    public Expression derivative(String var) {
        return new Number(0);
    }

    public double eval(String vals) {
        return value;
    }
}
