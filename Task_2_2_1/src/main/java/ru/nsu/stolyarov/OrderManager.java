package ru.nsu.stolyarov;

public class OrderManager extends SafeQueueManager{
    private int ordersCounter;
    private long limit;
    public OrderManager(int size, long limit) {
        super(size);
        ordersCounter = 0;
        this.limit = limit;
    }

    public long getLimit() {
        return limit;
    }

    public synchronized int takeOrder() throws InterruptedException {
        if(System.currentTimeMillis() >= limit){
            return -1;
        }
        add(ordersCounter);
        ordersCounter++;
        System.out.println("Заказ " + (ordersCounter - 1)  + " принят");
        return ordersCounter-1;
    }

}
