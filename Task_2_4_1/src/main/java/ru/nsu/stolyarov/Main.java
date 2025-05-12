package ru.nsu.stolyarov;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        MainController mainController = new MainController();
        ArrayList<String> repos = new ArrayList<>();
        ArrayList<ArrayList<String>> tasks = new ArrayList<>();

        repos.add("https://github.com/Dimandr1/OOP.git");
        tasks.add(new ArrayList<>());
        tasks.get(0).add("Task_1_1_1");

        mainController.act(repos, tasks);
    }
}