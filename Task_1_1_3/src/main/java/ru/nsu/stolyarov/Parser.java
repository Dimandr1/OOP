package ru.nsu.stolyarov;

/**
 * Класс для создания новых выражений.
 */
public class Parser {
    private int index;

    /**
     * Запускает обработку строки, чтобы получить из неё выражение.
     *
     * @param input - обрабатываемая строка
     * @return - выражение, эквивалентное строке
     */
    public Expression parseExpression(String input) {
        index = 0;
        return parseExpressionHelper(input);
    }

    /**
     * Находит одно выражение с операцией наименьшего приоритета помимо уже обработанных.
     *
     * @param input - обрабатываемая строка
     * @return - выражение с наименьшим приоритетом на текущий момент
     */
    private Expression parseExpressionHelper(String input) {
        if (input.charAt(index) >= '0' && input.charAt(index) <= '9') {
            double ans = 0;
            for (; index < input.length() && input.charAt(index) >= '0'
                    && input.charAt(index) <= '9';
                 index++) {
                ans *= 10;
                ans += input.charAt(index) - '0';
            }
            if (index < input.length() && input.charAt(index) == '.') {
                double coef = 1;
                index++;
                for (; index < input.length() && input.charAt(index) >= '0'
                        && input.charAt(index) <= '9';
                     index++) {
                    coef *= 10;
                    ans += (input.charAt(index) - '0') / coef;
                }
            }
            return new Number(ans);
        } else if (input.charAt(index) == '(') {
            index++;
            if (input.charAt(index) == '-') {
                double ans = 0;
                index++;
                for (; index < input.length() && input.charAt(index) >= '0'
                        && input.charAt(index) <= '9'; index++) {
                    ans *= 10;
                    ans -= input.charAt(index) - '0';
                }
                if (index < input.length() && input.charAt(index) == '.') {
                    double coef = 1;
                    index++;
                    for (; index < input.length() && input.charAt(index) >= '0'
                            && input.charAt(index) <= '9';
                         index++) {
                        coef *= 10;
                        ans -= (input.charAt(index) - '0') / coef;
                    }
                }
                index++;
                return new Number(ans);
            } else {
                Expression left = parseExpressionHelper(input);
                char operation = input.charAt(index);
                index++;
                Expression right = parseExpressionHelper(input);
                index++;
                if (operation == '+') {
                    return new Add(left, right);
                } else if (operation == '-') {
                    return new Sub(left, right);
                } else if (operation == '*') {
                    return new Mul(left, right);
                } else {
                    return new Div(left, right);
                }
            }
        } else {
            String ans = "";
            for (; index < input.length()
                    && ((input.charAt(index) >= 'a' && input.charAt(index) <= 'z')
                    || (input.charAt(index) >= 'A' && input.charAt(index) <= 'Z')); index++) {
                ans += input.charAt(index);
            }
            return new Variable(ans);
        }
    }
}
