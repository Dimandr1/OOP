package ru.nsu.stolyarov;

import ru.nsu.stolyarov.interfaces.OrderTaker;

/**
 * Класс, делающий заказы в пиццерию.
 */
public class Client {
    private OrderTaker ordersManager;

    /**
     * Инициализировать покупателя.
     *
     * @param ordersManager связанная с покупателем пиццерия
     */
    public Client(OrderTaker ordersManager) {
        this.ordersManager = ordersManager;
    }

    /**
     * Сделать заказ.
     */
    public void makeOrder() {
        ordersManager.takeOrder();
    }
}
