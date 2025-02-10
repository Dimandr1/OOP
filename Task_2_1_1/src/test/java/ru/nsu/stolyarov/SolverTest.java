package ru.nsu.stolyarov;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class SolverTest {
    @Test
    public void test() throws RuntimeException, FileNotFoundException, InterruptedException {
        ArrayList<Integer> test;
        test = new ArrayList<>();
        test.add(5);
        test.add(3);
        test.add(7);
        test.add(11);
        test.add(23);
        test.add(13);
        test.add(4);

        Solver testSolver = new Solver(test);
        assertEquals(true, testSolver.solve(1));
        assertEquals(true, testSolver.solve(4));
        assertEquals(true, testSolver.solve(8));
        assertEquals(true, testSolver.solve(16));
        assertEquals(true, testSolver.solve(0));

        test.remove(test.size() - 1);

        testSolver = new Solver(test);
        assertEquals(false, testSolver.solve(1));
        assertEquals(false, testSolver.solve(4));
        assertEquals(false, testSolver.solve(8));
        assertEquals(false, testSolver.solve(16));
        assertEquals(false, testSolver.solve(0));

        try (Scanner scanFile = new Scanner(new File("data.txt"))) {
            test.clear();
            while (scanFile.hasNext()) {
                int t = scanFile.nextInt();
                test.add(t);
            }
        }
        Solver timeTestSolver = new Solver(test);
        long startTime = System.currentTimeMillis();
        timeTestSolver.solve(1);
        long endTime = System.currentTimeMillis();
        System.out.println("Время работы без создания потоков: " + (endTime - startTime)
                + " милисекунд");

        startTime = System.currentTimeMillis();
        timeTestSolver.solve(2);
        endTime = System.currentTimeMillis();
        System.out.println("Время работы на 2 потоках (8 ядер): " + (endTime - startTime)
                + " милисекунд");

        startTime = System.currentTimeMillis();
        timeTestSolver.solve(3);
        endTime = System.currentTimeMillis();
        System.out.println("Время работы на 3 потоках (8 ядер): " + (endTime - startTime)
                + " милисекунд");

        startTime = System.currentTimeMillis();
        timeTestSolver.solve(4);
        endTime = System.currentTimeMillis();
        System.out.println("Время работы на 4 потоках (8 ядер): " + (endTime - startTime)
                + " милисекунд");

        startTime = System.currentTimeMillis();
        timeTestSolver.solve(5);
        endTime = System.currentTimeMillis();
        System.out.println("Время работы на 5 потоках (8 ядер): " + (endTime - startTime)
                + " милисекунд");

        startTime = System.currentTimeMillis();
        timeTestSolver.solve(6);
        endTime = System.currentTimeMillis();
        System.out.println("Время работы на 6 потоках (8 ядер): " + (endTime - startTime)
                + " милисекунд");

        startTime = System.currentTimeMillis();
        timeTestSolver.solve(7);
        endTime = System.currentTimeMillis();
        System.out.println("Время работы на 7 потоках (8 ядер): " + (endTime - startTime)
                + " милисекунд");

        startTime = System.currentTimeMillis();
        timeTestSolver.solve(8);
        endTime = System.currentTimeMillis();
        System.out.println("Время работы на 8 потоках (8 ядер): " + (endTime - startTime)
                + " милисекунд");

        startTime = System.currentTimeMillis();
        timeTestSolver.solve(9);
        endTime = System.currentTimeMillis();
        System.out.println("Время работы на 9 потоках (8 ядер): " + (endTime - startTime)
                + " милисекунд");

        startTime = System.currentTimeMillis();
        timeTestSolver.solve(10);
        endTime = System.currentTimeMillis();
        System.out.println("Время работы на 10 потоках (8 ядер): " + (endTime - startTime)
                + " милисекунд");

        startTime = System.currentTimeMillis();
        timeTestSolver.solve(11);
        endTime = System.currentTimeMillis();
        System.out.println("Время работы на 11 потоках (8 ядер): " + (endTime - startTime)
                + " милисекунд");

        startTime = System.currentTimeMillis();
        timeTestSolver.solve(12);
        endTime = System.currentTimeMillis();
        System.out.println("Время работы на 12 потоках (8 ядер): " + (endTime - startTime)
                + " милисекунд");


        startTime = System.currentTimeMillis();
        timeTestSolver.solve(0);
        endTime = System.currentTimeMillis();
        System.out.println("Время работы с помощью parallelStream " + (endTime - startTime)
                + " милисекунд");

    }
}