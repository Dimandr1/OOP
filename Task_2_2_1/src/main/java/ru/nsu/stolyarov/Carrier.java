package ru.nsu.stolyarov;

import java.util.ArrayList;

import ru.nsu.stolyarov.interfaces.QueueTimedGettable;

/**
 * Класс курьера доставки пиццулечки.
 */
public class Carrier {
    private int bagCapacity;
    private final long deliveryTime = 50;

    /**
     * Инициализация курьера с постоянным размером рюкзака.
     *
     * @param bagCapacity вместимость рюкзака в пиццах
     */
    public Carrier(int bagCapacity) {
        this.bagCapacity = bagCapacity;
    }

    /**
     * Курьер выходит на рабочий день.
     *
     * @param storageManager склад, откуда надо брать пиццулечку
     * @param limit          время работы в миллисекундах
     */
    public void workworkwork(QueueTimedGettable storageManager, long limit) {
        long startedWork = System.currentTimeMillis();
        while (limit > System.currentTimeMillis() - startedWork) {
            takeAndDeliver(storageManager,
                    limit - (System.currentTimeMillis() - startedWork));
        }
    }

    /**
     * Курьер набирает со склада пиццу, пока не заполнит рюкзак или не кончится рабочий день.
     * После этого доставляет.
     *
     * @param storageManager склад, откуда надо брать пиццулечку
     * @param limit          оставшееся время работы в миллисекундах
     * @return список доставленных заказов
     */
    public synchronized ArrayList<Integer> takeAndDeliver(QueueTimedGettable storageManager,
                                                          long limit) {
        long startTime = System.currentTimeMillis();
        ArrayList<Integer> pizzas = new ArrayList<>();
        while (pizzas.size() < bagCapacity
                && limit > System.currentTimeMillis() - startTime) {
            int order = storageManager.tryGet(
                    limit - (System.currentTimeMillis() - startTime));
            if (order != -1) {
                pizzas.add(order);
            }
        }
        for (Integer order : pizzas) {
            try {
                wait(deliveryTime);
            } catch (InterruptedException e) {
                System.out.println("everything is fine");
            }
            System.out.println("Заказ " + order + " доставлен");
        }
        return pizzas;
    }
}
