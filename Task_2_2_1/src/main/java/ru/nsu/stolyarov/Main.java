package ru.nsu.stolyarov;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;

/**
 * main lol.
 */
public class Main {
    /**
     * lol main.
     */
    public static void main() {
        Configuration myConf = new Configuration();
        myConf.bakers = new long[]{300, 500, 700, 400, 250};
        myConf.carriers = new int[]{2, 1, 3, 1, 2};
        myConf.storageCapacity = 20;
        myConf.workingTime = 10000;
        myConf.sleepTime = 3000;

        String json = (new Gson()).toJson(myConf);

        try (FileWriter writer = new FileWriter("pizza.json")) {
            writer.write(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}