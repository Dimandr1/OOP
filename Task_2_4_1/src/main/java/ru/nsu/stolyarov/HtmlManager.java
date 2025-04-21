package ru.nsu.stolyarov;

import java.util.ArrayList;

public class HtmlManager {

    public static void printRes(ArrayList<Task> tasks) {
        for(int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getFirst();
            StringBuilder sb = new StringBuilder();
            sb.append("Student = ");
            sb.append(task.studentName);
            sb.append("Task = ");
            sb.append(task.taskName);
            sb.append("build = ");
            sb.append(task.build);
            sb.append(" | docs = ");
            sb.append(task.docs);
            sb.append(" | style = ");
            sb.append(task.style);
            sb.append(" | coverage = ");
            sb.append(task.testsCoverage);
            sb.append(" | bonus = ");
            sb.append(task.bonus);
            sb.append(" | total = ");
            sb.append(task.total);
            System.out.println(sb.toString());
        }
    }
}
