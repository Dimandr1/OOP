package ru.nsu.stolyarov;

/**
 * Бинарное деление
 */
public class Div extends Expression{
    private Expression first, second;

    /**
     * Инициализация бинарного деления.
     * @param first - выражение слева от знака
     * @param second - выражение справа от знака
     */
    public Div(Expression first, Expression second){
        this.first = first;
        this.second = second;
    }
    public String print(){
        return "(" + first.print() + "/" + second.print() + ")";
    }
    public Expression derivative(String var){
        return new Div(new Sub(new Mul(first.derivative(var), second),
                new Mul(first, second.derivative(var))), new Mul(second, second));
    }
    public int eval(String vals){
        return first.eval(vals) / second.eval(vals);
    }
}
