package ru.nsu.stolyarov;

public class Main {
    public static void main(String[] args) {
        Configuration myConf = new Configuration();
        myConf.bakers = new int[]{100, 200, 300, 50, 150};
        myConf.carriers = new int[]{2, 1, 3, 1, 2};
        myConf.storageCapacity = 20;
        myConf.workingTime = 10000;
        myConf.sleepTime = 3000;

    }
}