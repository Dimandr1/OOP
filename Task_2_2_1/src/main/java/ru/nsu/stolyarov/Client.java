package ru.nsu.stolyarov;

public class Client {
    private OrderManager ordersManager;
    public Client(OrderManager ordersManager){
        this.ordersManager = ordersManager;
    }

    public void makeOrder() throws InterruptedException {
        ordersManager.takeOrder();
    }
}
