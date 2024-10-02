package ru.nsu.stolyarov;

/**
 * Переменная.
 */
public class Variable extends Expression {
    private String name;

    /**
     * Инициализация переменной.
     *
     * @param name - имя переменной.
     */
    public Variable(String name) {
        this.name = name;
    }

    public String print() {
        return name;
    }

    public Expression derivative(String var) {
        if (var.equals(name)) {
            return new Number(1);
        } else {
            return new Number(0);
        }
    }

    public int eval(String vals) {
        int ans = 0;
        int ind = 0;
        do {
            ind = vals.indexOf(name, ind) + name.length();
        } while (vals.charAt(ind) != ' ');
        ind += 3;
        if (vals.charAt(ind) == '-') {
            ind++;
            for (; ind < vals.length() && vals.charAt(ind) != ';'; ind++) {
                ans *= 10;
                ans -= vals.charAt(ind) - '0';
            }
        } else {
            for (; ind < vals.length() && vals.charAt(ind) != ';'; ind++) {
                ans *= 10;
                ans += vals.charAt(ind) - '0';
            }
        }
        return ans;
    }
}
