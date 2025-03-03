package ru.nsu.stolyarov;

import ru.nsu.stolyarov.interfaces.QueueTimedAddable;
import ru.nsu.stolyarov.interfaces.QueueTimedGettable;

/**
 * Класс повара. Берет открытые заказы, готовит пиццулечку, относит на склад.
 */
public class Baker {
    private long cookingDelay;

    /**
     * Инициализация повара.
     *
     * @param cookingDelay время на приготовление одной пиццы
     */
    public Baker(long cookingDelay) {
        this.cookingDelay = cookingDelay;
    }

    /**
     * Повар выходит на рабочий день.
     *
     * @param orderManager   откуда брать заказы
     * @param storageManager куда складывать пиццулечку
     * @param limit          время работы
     */
    public void workworkwork(QueueTimedGettable orderManager,
                             QueueTimedAddable storageManager, long limit) {
        long startedWork = System.currentTimeMillis();
        while (limit > System.currentTimeMillis() - startedWork) {
            cookAndPut(orderManager, storageManager,
                    limit - (System.currentTimeMillis() - startedWork));
        }
    }

    /**
     * Повар пытается взять заказ. Если получилось, то он приготовит пиццу и отнесет на склад.
     *
     * @param orderManager   откуда брать заказ
     * @param storageManager куда складывать
     * @param limit          оставшееся время до конца работы
     * @return номер приготовленного заказа или -1 в случае неудачи
     */
    public synchronized int cookAndPut(QueueTimedGettable orderManager,
                                       QueueTimedAddable storageManager, long limit) {
        int order = orderManager.tryGet(limit);
        if (order != -1) {
            System.out.println("Заказ " + order + " готовится");
            try {
                wait(cookingDelay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            storageManager.tryAdd(order, 0);
            System.out.println("Заказ " + order + " поступил на склад");
        }
        return order;
    }


}
