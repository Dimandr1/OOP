package ru.nsu.stolyarov;

public class HtmlManager {

    public static void printRes(Task task){
        StringBuilder sb = new StringBuilder();
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
