package ru.nsu.stolyarov.task_2_3_1;

import javafx.util.Pair;

/**
 * Реализация очереди из пар натуральных чисел на основе кольцевого буфера.
 */
public class SnakeDequeue {
    private Pair<Integer, Integer>[] orderQueue;
    private int left;
    private int right;
    public int size;

    /**
     * Инициализация очереди.
     *
     * @param n максимальный размер очереди
     */
    public SnakeDequeue(int n) {
        orderQueue = new Pair[n + 1];
        left = 0;
        right = 0;
        size = n + 1;
    }

    /**
     * Возвращает количество элементов в очереди.
     *
     * @return количество элементов в очереди
     */
    public int len() {
        if (right >= left) {
            return right - left;
        }
        return size - (left - right);
    }

    /**
     * Добавить элемент в начало очереди.
     *
     * @param element натуральное число для добавления
     * @throws ArrayIndexOutOfBoundsException возникает при попытке добавить элемент в
     *                                        заполненную очередь
     */
    public void addStart(Pair element) throws ArrayIndexOutOfBoundsException {
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
     * Изъять элемент из конца очереди.
     *
     * @return значение последнего элемента в очереди (натуральное число)
     * @throws ArrayIndexOutOfBoundsException возникает при попытке изъять элемент из
     *                                        пустой очереди
     */
    public Pair extractLast() throws ArrayIndexOutOfBoundsException {
        if (len() == 0) {
            throw new ArrayIndexOutOfBoundsException("empty queue!");
        }
        Pair ret = orderQueue[left];
        left++;
        if (left == size) {
            left = 0;
        }
        return ret;
    }

    /**
     * Посмотреть на эллемент в конце очереди.
     *
     * @return значение последнего элемента в очереди (натуральное число)
     * @throws ArrayIndexOutOfBoundsException возникает при попытке взять элемент из
     *                                        пустой очереди
     */
    public Pair getLast() throws ArrayIndexOutOfBoundsException {
        if (len() == 0) {
            throw new ArrayIndexOutOfBoundsException("empty queue!");
        }
        Pair ret = orderQueue[left];
        return ret;
    }

    public void returnLastEl(){
        if(left == 0){
            left = size-1;
        }
        else{
            left--;
        }
    }
}