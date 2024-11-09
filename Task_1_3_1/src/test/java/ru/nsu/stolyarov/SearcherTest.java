package ru.nsu.stolyarov;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SearcherTest {
    @Test
    void test() {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("input.txt"), "UTF-8"))) {
            writer.write("абракадабра");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {

            File inputtxt = new File("input.txt");
            ArrayList<Integer> testList = new ArrayList<>();
            testList.add(1);
            testList.add(8);
            assertEquals(testList, Searcher.getSubstrings(inputtxt, "бра"));
            assertEquals("[1, 8]", Searcher.find("input.txt", "бра"));
            assertEquals("[0, 7]", Searcher.find("input.txt", "аб"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("input.txt"), "UTF-8"))) {
            String base = "$¢€ƕ";
            writer.write(base);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            assertEquals("[3]", Searcher.find("input.txt", "ƕ"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("input.txt"), "UTF-8"))) {
            String base = "abbabaaaab";
            long n = (long) 1e4;
            for (int i = 0; i < n; i++) {
                writer.write(base);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Searcher.find("input.txt", "aba");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}