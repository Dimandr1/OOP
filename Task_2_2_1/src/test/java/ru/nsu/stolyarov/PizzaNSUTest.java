package ru.nsu.stolyarov;

import org.junit.jupiter.api.Test;

class PizzaNSUTest {
    @Test
    void test() throws InterruptedException {
        Configuration myConf = new Configuration();
        myConf.bakers = new int[]{300, 500, 700, 400, 250};
        myConf.carriers = new int[]{2, 1, 3, 1, 2};
        myConf.storageCapacity = 20;
        myConf.workingTime = 10000;
        myConf.sleepTime = 3000;

        PizzaNsU pizzaHub = new PizzaNsU(myConf);
        Thread hubThread = new Thread(() -> {
            try {
                pizzaHub.start(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        hubThread.start();

        Client client = new Client(pizzaHub.getOrders());
        synchronized (this) {
            for (int i = 0; i < 300; i++) {
                client.makeOrder();
                wait(50);
            }
        }

        hubThread.join();
    }
}