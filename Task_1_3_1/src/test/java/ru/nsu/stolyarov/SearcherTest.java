package ru.nsu.stolyarov;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SearcherTest {
    @Test
    void test() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("input.txt"), "UTF-8"))) {
            writer.write("абракадабра");
        }
        File inputtxt = new File("input.txt");
        ArrayList<Long> testList = new ArrayList<>();
        testList.add(1L);
        testList.add(8L);
        assertEquals(testList, Searcher.getSubstrings(inputtxt, "бра"));
        assertEquals("[1, 8]", Searcher.find("input.txt", "бра"));
        assertEquals("[0, 7]", Searcher.find("input.txt", "аб"));

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("input.txt"), "UTF-8"))) {
            String base = "$¢€ƕ";
            writer.write(base);
        }
        assertEquals("[3]", Searcher.find("input.txt", "ƕ"));


        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("input.txt"), "UTF-8"))) {
            String base = "abbabaaaab";
            long n = (long) 1e4;
            for (int i = 0; i < n; i++) {
                writer.write(base);
            }
        }
        Searcher.find("input.txt", "aba");


        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("input.txt"), "UTF-8"))) {
            String base = "123\uD83D\uDE00123\uD83D\uDE00";
            writer.write(base);
        }
        assertEquals("[3, 7]", Searcher.find("input.txt", "\uD83D\uDE00"));


    }

}