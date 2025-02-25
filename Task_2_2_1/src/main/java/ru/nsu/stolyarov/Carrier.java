package ru.nsu.stolyarov;

import java.util.ArrayList;

public class Carrier {
    private int bagCapacity;
    private SafeQueueManager storageManager;
    private OrderManager ordersManager;
    private long limit;
    public Carrier(int bagCapacity, OrderManager ordersManager, SafeQueueManager storageManager){
        this.bagCapacity = bagCapacity;
        this.ordersManager = ordersManager;
        this.storageManager = storageManager;
    }
    public void workworkwork() throws InterruptedException {
        while(limit > System.currentTimeMillis() || storageManager.getFullness() > 0){
            ArrayList<Integer> pizzas = new ArrayList<>();
            int order;
            while(pizzas.size() < bagCapacity && ((limit > System.currentTimeMillis() &&
                    (order = storageManager.tryGet(limit)) != -1) ||
                    ((limit <= System.currentTimeMillis()) &&
                            (order = storageManager.tryGet()) != -1))){
                pizzas.add(order);
                System.out.println("Заказ " + order + " взят курьером");
            }
            for(int i = 0; i < pizzas.size(); i++){
                this.wait(50);
                System.out.println("Заказ " + pizzas.get(i) + " доставлен");
            }
        }
    }
}
