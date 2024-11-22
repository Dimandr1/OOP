package ru.nsu.stolyarov;

public class Main {
    public static void main(String[] args) {
        HashTable<String, Integer> testingTable = new HashTable<String, Integer>();
        testingTable.put("one", 1);
        testingTable.put("three", 3);
        testingTable.put("five", 5);
        testingTable.put("minus four", -4);
        testingTable.put("two", 2);
        System.out.println(testingTable.toStr());
    }
}