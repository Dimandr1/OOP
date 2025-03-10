package ru.nsu.stolyarov;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Класс, представляющий пиццерию в целом. Запускает "ежедневно" рабочий процесс.
 */
public class PizzaNsu {
    private SafeQueueManager storage;
    private OrderManager orders;
    private ArrayList<Baker> bakers;
    private ArrayList<Carrier> carriers;
    private long workingTime;
    private long sleepTime;
    private static int callsCapacity = 99999;

    /**
     * Создание пиццерии по объекту конфигурации..
     *
     * @param configuration объект конфигурации
     */
    public PizzaNsu(Configuration configuration) {
        SetStartConf(configuration);
    }

    /**
     * Создание пиццерии по JSON-файлу с конфигурацией.
     *
     * @param pathToConf путь до файла конфигурации
     */
    public PizzaNsu(String pathToConf) {
        Configuration configuration;
        try (FileReader reader = new FileReader(pathToConf)) {
            configuration = (new Gson()).fromJson(reader, Configuration.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SetStartConf(configuration);
    }

    /**
     * Инициализация полей пиццерии по заданной конфигурации.
     *
     * @param configuration класс конфигурации пиццерии
     */
    private void SetStartConf(Configuration configuration) {
        storage = new SafeQueueManager(configuration.storageCapacity);
        orders = new OrderManager(callsCapacity);
        bakers = new ArrayList<>();
        carriers = new ArrayList<>();
        for (long i : configuration.bakers) {
            bakers.add(new Baker(i));
        }
        for (int i : configuration.carriers) {
            carriers.add(new Carrier(i));
        }
        workingTime = configuration.workingTime;
        sleepTime = configuration.sleepTime;
    }

    /**
     * Запуск пиццерии, которая будет работать заданное число дней.
     *
     * @param workingDays сколько дней работать.
     *                    При отрицательных значениях работает бесконечно
     * @throws InterruptedException
     */
    public synchronized void start(int workingDays) throws InterruptedException {
        int i = 0;
        while (i < workingDays) {
            startWork();
            System.out.println("Пиццерия закончила работу, все ушли спать");
            wait(sleepTime);
            i++;
        }
    }

    /**
     * Запускает рабочий процесс в пиццерии и ждёт, пока все доработают.
     */
    private void startWork() {
        System.out.println("В пиццерии начался рабочий день");
        ArrayList<Thread> threads = new ArrayList<>();

        for (Baker i : bakers) {
            threads.add(new Thread(() -> i.workworkwork(orders, storage, workingTime)));
        }
        for (Carrier i : carriers) {
            threads.add(new Thread(() -> i.workworkwork(storage, workingTime)));
        }
        for (Thread i : threads) {
            i.start();
        }
        for (Thread i : threads) {
            try {
                i.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public OrderManager getOrders() {
        return orders;
    }
}
