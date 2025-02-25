package ru.nsu.stolyarov;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void test() throws InterruptedException {
        ArrayList<Thread> threads = new ArrayList<>();
        OrderManager orderManager = new OrderManager(20, System.currentTimeMillis() + 1000 * 30);
        SafeQueueManager storageManager = new SafeQueueManager(20);
        Client client = new Client(orderManager);
        for(int i = 0; i < 4; i++){
            Baker baker = new Baker((i+1)*100, orderManager, storageManager);
            Thread thread = new Thread(() -> {
                try {
                    baker.workworkwork();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            thread.start();
            threads.add(thread);

            Carrier carrier = new Carrier(i+1, orderManager, storageManager);
            thread = new Thread(() -> {
                try {
                    carrier.workworkwork();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            thread.start();
            threads.add(thread);
        }
        for(int i = 0; i < 100; i++){
            client.makeOrder();
        }
        for(int i = 0; i < threads.size();i++){
            threads.get(i).join();
        }
    }
}