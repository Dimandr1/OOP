package ru.nsu.stolyarov;

/**
 * Бинарное умножение.
 */
public class Mul extends Expression {
    private Expression first;
    private Expression second;

    /**
     * Инициализация бинарного умножения.
     *
     * @param first  - выражение слева от знака
     * @param second - выражение справа от знака
     */
    public Mul(Expression first, Expression second) {
        this.first = first;
        this.second = second;
    }

    public String print() {
        return "(" + first.print() + "*" + second.print() + ")";
    }

    public Expression derivative(String var) {
        return new Add(new Mul(first.derivative(var), second),
                new Mul(first, second.derivative(var)));
    }

    public double eval(String vals) {
        return first.eval(vals) * second.eval(vals);
    }
}
