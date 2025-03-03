package ru.nsu.stolyarov.interfaces;

/**
 * Интерфейс объектов, которые могут принимать заказы.
 */
public interface OrderTaker {
    /**
     * Принимает заказ на пиццу и возвращает id заказа, если заказ успешно добавлен.
     * Возвращает -1 иначе.
     *
     * @return - id заказа или -1 при неудаче
     */
    public int takeOrder();
}
