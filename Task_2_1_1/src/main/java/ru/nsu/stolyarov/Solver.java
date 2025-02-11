package ru.nsu.stolyarov;

import java.util.ArrayList;

/**
 * Использование потоков.
 */
public class Solver {

    private ArrayList<Integer> digits;
    private boolean flag;
    private int cur;

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
    private synchronized int increment() {
        cur++;
        return cur - 1;
    }

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
        cur = 0;

        if (threadsAmount == 1) {
            runThread();
        } else if (threadsAmount == 0) {
            if (digits.parallelStream().map(n -> isPrime(n))
                    .filter(n -> !n).toList().isEmpty()) {
                return false;
            } else {
                return true;
            }

        } else if (threadsAmount > 1) {
            ArrayList<Thread> threads = new ArrayList<>();
            for (int i = 0; i < threadsAmount; i++) {
                Thread anotherThread = new Thread(this::runThread);
                threads.add(anotherThread);
                anotherThread.start();
            }
            for (int i = 0; i < threadsAmount; i++) {
                threads.get(i).join();
            }
        }

        return flag;
    }

    /**
     * Функция для создания потока, проверяющего числа массива на простоту.
     */
    private void runThread() {
        while (!flag && cur < digits.size()) {
            int t = increment();
            if (t < digits.size() && !isPrime(digits.get(t))) {
                flag = true;
            }
        }
    }
}
