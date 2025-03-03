package ru.nsu.stolyarov;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Реализация очереди натуральных элементов на основе кольцевого буфера.
 */
public class MyQueue {
    private int[] orderQueue;
    private int left;
    private int right;
    public int size;

    /**
     * Инициализация очереди
     *
     * @param n максимальный размер очереди
     */
    public MyQueue(int n) {
        orderQueue = new int[n + 1];
        left = 0;
        right = 0;
        size = n + 1;
    }

    /**
     * @return количество элементов в очереди
     */
    public int len() {
        if (right >= left) {
            return right - left;
        }
        return size - (left - right);
    }

    /**
     * Добавить элемент в очередь.
     *
     * @param element натуральное число для добавления
     * @throws ArrayIndexOutOfBoundsException возникает при попытке добавить элемент в
     *                                        заполненную очередь
     */
    public void add(int element) throws ArrayIndexOutOfBoundsException {
        if (len() == size - 1) {
            throw new ArrayIndexOutOfBoundsException("queue is full!");
        }
        orderQueue[right] = element;
        right++;
        if (right == size) {
            right = 0;
        }
    }

    /**
     * Изъять элемент из начала очереди.
     *
     * @return значение первого элемента в очереди (натуральное число)
     * @throws ArrayIndexOutOfBoundsException возникает при попытке изъять элемент из
     *                                        пустой очереди
     */
    public int extract() throws ArrayIndexOutOfBoundsException {
        if (len() == 0) {
            throw new ArrayIndexOutOfBoundsException("empty queue!");
        }
        int ret = orderQueue[left];
        left++;
        if (left == size) {
            left = 0;
        }
        return ret;
    }
}
