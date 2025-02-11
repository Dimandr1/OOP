package ru.nsu.stolyarov;

import java.util.ArrayList;

/**
 * Использование потоков.
 */
public class Solver {

    private ArrayList<Integer> digits;
    private boolean flag;

    /**
     * Проверка числа на простоту.
     *
     * @param digit - проверяемое число
     * @return - простое ли (true/false)
     */
    private boolean isPrime(int digit) {
        for (int i = 2; i * i <= digit; i++) {
            if (digit % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Увеличить cur (индекс числа в массиве, которое мы сейчас обрабатываем) на 1
     *
     * @return старое значение cur
     */

    /**
     * Создать решатель, передавая обрабатываемый массив.
     *
     * @param ar - тот самый массив
     */
    public Solver(ArrayList<Integer> ar) {
        digits = ar;
    }

    /**
     * Обработать заданный при создании решателя массив, находя в нем простые.
     *
     * @param threadsAmount - количество потоков обработки
     * @return есть ли в массиве не простые числа
     * @throws InterruptedException
     */
    public boolean solve(int threadsAmount) throws InterruptedException {
        flag = false;

        if (threadsAmount == 1) {
            runThread(0, digits.size());
        } else if (threadsAmount == 0) {
            if (digits.parallelStream().anyMatch(n -> !isPrime(n))) {
                return true;
            } else {
                return false;
            }

        } else if (threadsAmount > 1) {
            if (threadsAmount > digits.size()) {
                threadsAmount = digits.size();
            }
            ArrayList<Thread> threads = new ArrayList<>();
            int part = (digits.size() - 1) / threadsAmount + 1;
            for (int i = 0; i < threadsAmount - 1; i++) {
                final int left = part * i;
                final int right = part * (i + 1);
                Thread anotherThread = new Thread(() -> runThread(left, right));
                threads.add(anotherThread);
                anotherThread.start();
            }
            final int left = part * (threadsAmount - 1);
            final int right = digits.size();
            Thread anotherThread = new Thread(() -> runThread(left, right));
            threads.add(anotherThread);
            anotherThread.start();
            for (int i = 0; i < threadsAmount; i++) {
                threads.get(i).join();
            }
        }

        return flag;
    }

    /**
     * Функция для создания потока, проверяющего числа массива на простоту.
     * @param from индекс элемента, с которого начнем проверять (включительно)
     * @param to индекс элемента, на котором закончим проверять (не включительно)
     */
    private void runThread(final int from, final int to) {
        int i = from;
        while (!flag && i < to) {
            if (!isPrime(digits.get(i))) {
                flag = true;
            }
            i++;
        }
    }
}
