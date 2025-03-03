package ru.nsu.stolyarov;

import ru.nsu.stolyarov.interfaces.OrderTaker;

/**
 * Служба обработки заказов.
 */
public class OrderManager extends SafeQueueManager implements OrderTaker {
    private int ordersCounter;

    /**
     * Инициализация.
     *
     * @param size максимальное количество заказов, которые уже получены,
     *             но еще не отправлены готовиться.
     */
    public OrderManager(int size) {
        super(size);
        ordersCounter = 0;
    }


    @Override
    public synchronized int takeOrder() {
        tryAdd(ordersCounter, 50);
        ordersCounter++;
        System.out.println("Заказ " + (ordersCounter - 1) + " принят");
        return ordersCounter - 1;
    }

}
