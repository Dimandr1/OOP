package ru.nsu.stolyarov;

/**
 * Бинарное сложение.
 */
public class Add extends Expression {
    private Expression first;
    private Expression second;

    /**
     * Инициализация бинарного сложения.
     *
     * @param first  - выражение слева от знака
     * @param second - выражение справа от знака
     */
    public Add(Expression first, Expression second) {
        this.first = first;
        this.second = second;
    }

    public String print() {
        return "(" + first.print() + "+" + second.print() + ")";
    }

    public Expression derivative(String var) {
        return new Add(first.derivative(var), second.derivative(var));
    }

    public int eval(String vals) {
        return first.eval(vals) + second.eval(vals);
    }
}
