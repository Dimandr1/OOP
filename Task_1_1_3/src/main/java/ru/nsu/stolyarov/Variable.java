package ru.nsu.stolyarov;

/**
 * Переменная.
 */
public class Variable extends Expression {
    private String varName;

    /**
     * Инициализация переменной.
     *
     * @param name - имя переменной.
     */
    public Variable(String name) {
        varName = name;
    }

    public String print() {
        return varName;
    }

    public Expression derivative(String var) {
        if (var.equals(varName)) {
            return new Number(1);
        } else {
            return new Number(0);
        }
    }

    public double eval(String vals) {
        double ans = 0;
        int ind = 0;
        do {
            ind = vals.indexOf(varName, ind);
            if (ind == -1) {
                break;
            }
            ind += varName.length();
        } while (vals.charAt(ind) != ' ');
        if (ind == -1) {
            return 0;
        }
        ind += 3;
        if (vals.charAt(ind) == '-') {
            ind++;
            for (; ind < vals.length() && vals.charAt(ind) != '.'
                    && vals.charAt(ind) != ';'; ind++) {
                ans *= 10;
                ans -= vals.charAt(ind) - '0';
            }
            if (ind < vals.length() && vals.charAt(ind) == '.') {
                double coef = 1;
                ind++;
                for (; ind < vals.length() && vals.charAt(ind) != ';';
                     ind++) {
                    coef *= 10;
                    ans -= (vals.charAt(ind) - '0') / coef;
                }
            }
        } else {
            for (; ind < vals.length() && vals.charAt(ind) != '.'
                    && vals.charAt(ind) != ';'; ind++) {
                ans *= 10;
                ans += vals.charAt(ind) - '0';
            }
            if (ind < vals.length() && vals.charAt(ind) == '.') {
                double coef = 1;
                ind++;
                for (; ind < vals.length() && vals.charAt(ind) != ';';
                     ind++) {
                    coef *= 10;
                    ans += (vals.charAt(ind) - '0') / coef;
                }
            }
        }
        return ans;
    }
}
