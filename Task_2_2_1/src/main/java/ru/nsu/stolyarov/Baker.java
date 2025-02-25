package ru.nsu.stolyarov;

public class Baker {
    private long cookingDelay;
    private SafeQueueManager storageManager;
    private OrderManager ordersManager;
    public Baker(long cookingDelay, OrderManager ordersManager, SafeQueueManager storageManager){
        this.cookingDelay = cookingDelay;
        this.ordersManager = ordersManager;
        this.storageManager = storageManager;
    }
    public void workworkwork() throws InterruptedException {
        while(ordersManager.getLimit() > System.currentTimeMillis() || ordersManager.getFullness() > 0){
            int order;
            if((ordersManager.getLimit() > System.currentTimeMillis() &&
                    (order = ordersManager.tryGet(ordersManager.getLimit())) != -1) ||
                    ((ordersManager.getLimit() <= System.currentTimeMillis()) &&
                            (order = ordersManager.tryGet()) != -1)){
                System.out.println("Заказ " + order + " готовится");
                this.wait(cookingDelay);
                storageManager.add(order);
                System.out.println("Заказ " + order + " поступил на склад");
            }
        }
    }



}
