package ru.nsu.stolyarov;

public class Task {
    public String taskName;
    public String studentName;
    public boolean build;
    public boolean docs;
    public boolean style;
    public int testsCoverage;
    public int bonus;
    public int total;

    public Task(String taskName, String studentName) {
        this.taskName = taskName;
        this.studentName = studentName;
        build = false;
        docs = false;
        style = false;
        testsCoverage = 0;
        bonus = 0;
        total = 0;
    }
}
