package ru.nsu.stolyarov;

import java.util.Scanner;

/**
 * Operations with equations.
 *
 * @author Dmitry Stolyarov
 */
public class Main {
    /**
     * Здесь просто прикрученный к остальным классам ввод-вывод для тестирования.
     *
     * @param args - wtf is it
     */
    public static void main(String[] args) {
        Parser parser = new Parser();
        Scanner scan = new Scanner(System.in);
        System.out.println("Commands:\n1. new - enter new expression\n" +
                "2. eval - evaluate current expression\n" +
                "3. der - take a derivative of the function" +
                "4. anything else will end the prograg");
        boolean cont = true;
        Expression currentExpression = null;
        while (cont) {
            if (currentExpression != null) {
                System.out.println("Current expression is: " + currentExpression.print());
            }
            System.out.println("Enter command:");
            String command = scan.next();
            if (command.equals("new")) {
                System.out.println("Enter expression:");
                String exp = scan.next();
                currentExpression = parser.parseExpression(exp);
            } else if (command.equals("eval")) {
                if (currentExpression == null) {
                    System.out.println("No current expression");
                } else {
                    System.out.println("Enter values of variables " +
                            "(or leave empty if there are no variables:");
                    scan.nextLine();
                    String vals = scan.nextLine();
                    System.out.println(currentExpression.eval(vals));
                }
            } else if (command.equals("der")) {
                if (currentExpression == null) {
                    System.out.println("No current expression");
                } else {
                    System.out.println("Enter name of variable:");
                    String var = scan.next();
                    currentExpression = currentExpression.derivative(var);
                }
            } else {
                cont = false;
                System.out.println("Ending");
            }
        }
    }
}