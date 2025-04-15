package ru.nsu.stolyarov;

import java.io.*;
import java.util.ArrayList;

public class TaskChecker {

    public static Task processTask(String folderPath, String taskName) throws IOException, InterruptedException {
        Task task = new Task();
        if (!changeBranch(taskName, folderPath)) {
            return task;
        }

        StringBuilder sb = new StringBuilder(folderPath);
        sb.append("/");
        sb.append(taskName);
        String taskPath = sb.toString();


        if (!build(taskPath, task)) {
            return task;
        }

        if (!docs(taskPath, task)) {
            return task;
        }

        /*if(!style(taskPath)){
            return;
        }*/
        task.style = true;

        if (!test(taskPath, task)) {
            return task;
        }

        countPoints(task);

        return task;
    }

    private static boolean changeBranch(String branchName, String folderPath) throws IOException, InterruptedException {
        ProcessBuilder processBuilder =
                new ProcessBuilder("git", "switch", branchName);
        processBuilder.directory(new File(folderPath));
        processBuilder.redirectErrorStream(true);
        Process proc = processBuilder.start();
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(proc.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        int exitCode = proc.waitFor();
        return (exitCode > 0 ? false : true);
    }

    private static boolean build(String folderPath, Task task) throws IOException, InterruptedException {
        ProcessBuilder processBuilder =
                new ProcessBuilder("./gradlew", "build");
        processBuilder.directory(new File(folderPath));
        processBuilder.redirectErrorStream(true);
        Process proc = processBuilder.start();
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(proc.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        int exitCode = proc.waitFor();
        boolean res = (exitCode > 0 ? false : true);

        if (res) {
            task.build = true;
        } else {
            task.build = false;
        }

        return res;
    }

    private static boolean docs(String folderPath, Task task) throws IOException, InterruptedException {
        ProcessBuilder processBuilder =
                new ProcessBuilder("./gradlew", "javadoc");
        processBuilder.directory(new File(folderPath));
        processBuilder.redirectErrorStream(true);
        Process proc = processBuilder.start();
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(proc.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        int exitCode = proc.waitFor();
        boolean res = (exitCode > 0 ? false : true);

        if (res) {
            task.docs = true;
        } else {
            task.docs = false;
        }

        return res;
    }


    /*private static boolean style(String folderPath) throws IOException, InterruptedException {
        ProcessBuilder processBuilder =
                new ProcessBuilder("", "check");
        processBuilder.directory(new File(folderPath));
        processBuilder.redirectErrorStream(true);
        Process proc = processBuilder.start();
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(proc.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        int exitCode = proc.waitFor();
        return (exitCode > 0 ? false : true);
        processBuilder =
                new ProcessBuilder("./gradlew", "check");
        processBuilder.directory(new File(folderPath));
        processBuilder.redirectErrorStream(true);
        Process proc = processBuilder.start();
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(proc.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        int exitCode = proc.waitFor();
        return (exitCode > 0 ? false : true);

    }*/

    private static boolean test(String folderPath, Task task) throws IOException, InterruptedException {
        ProcessBuilder processBuilder =
                new ProcessBuilder("./gradlew", "jacocoTestReport");
        processBuilder.directory(new File(folderPath));
        processBuilder.redirectErrorStream(true);
        Process proc = processBuilder.start();
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(proc.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        int exitCode = proc.waitFor();
        boolean res = (exitCode > 0 ? false : true);

        StringBuilder sbpath = new StringBuilder(folderPath);
        sbpath.append("/build/reports/jacoco/test/html/index.html");
        String indpath = sbpath.toString();
        try (BufferedReader reader2 = new BufferedReader(new FileReader(indpath))) {
            StringBuilder htmlContent = new StringBuilder();
            while ((line = reader2.readLine()) != null) {
                htmlContent.append(line).append("\n");
            }
            String htmlTests = htmlContent.toString();
            int totalInd = htmlTests.indexOf("Total");
            int percentInd = htmlTests.indexOf('%', totalInd);
            int i;
            for (i = percentInd; htmlTests.charAt(i) != '>'; i--) ;
            i++;
            int cov = 0;
            for (; htmlTests.charAt(i) >= '0' && htmlTests.charAt(i) <= '9'; i++) {
                cov *= 10;
                cov += htmlTests.charAt(i) - '0';
            }
            task.testsCoverage = cov;

        } catch (IOException e) {
            res = false;
        }

        if (res == false) {
            task.testsCoverage = 0;
        }
        return res;
    }

    private static boolean countPoints(Task task) {
        task.total = 0;
        if (task.build && task.docs && task.style && task.testsCoverage >= 80) {
            task.total++;
        }
        return true;
    }
}
