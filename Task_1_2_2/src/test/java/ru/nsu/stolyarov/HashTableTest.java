package ru.nsu.stolyarov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class HashTableTest {
    @Test
    void test() {
        HashTable<String, Integer> testingTable = new HashTable<String, Integer>();
        testingTable.put("one", 1);
        testingTable.put("three", 3);
        testingTable.put("five", 5);
        testingTable.put("minus four", -4);
        testingTable.put("two", 2);
        assertEquals(true, testingTable.exists("five"));
        assertEquals(-4, testingTable.get("minus four"));
        testingTable.del("five");
        assertEquals(false, testingTable.exists("five"));
        assertEquals(2, testingTable.get("two"));
        testingTable.update("two", 123);
        assertEquals(123, testingTable.get("two"));


        HashTable<String, Integer> anotherTable = new HashTable<String, Integer>();
        anotherTable.put("one", 1);
        anotherTable.put("minus four", -4);
        anotherTable.put("two", 123);
        assertEquals(true, anotherTable.toStr().contains("one = 1\n"));
        assertEquals(true, anotherTable.toStr().contains("minus four = -4\n"));
        assertEquals(true, anotherTable.toStr().contains("two = 123\n"));
        assertEquals(false, testingTable.tableEquals(anotherTable));
        anotherTable.put("three", 3);
        assertEquals(true, testingTable.tableEquals(anotherTable));

    }
}