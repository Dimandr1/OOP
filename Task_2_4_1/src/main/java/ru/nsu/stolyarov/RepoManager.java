package ru.nsu.stolyarov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RepoManager {
    public static boolean repoDownload(String downloadUrl) throws IOException, InterruptedException {
        ProcessBuilder processBuilder =
                new ProcessBuilder("git", "clone", downloadUrl);
        processBuilder.redirectErrorStream(true);
        Process proc = processBuilder.start();

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(proc.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        // Ожидаем завершения
        int exitCode = proc.waitFor();
        System.out.println("Exited with code: " + exitCode);
        if (exitCode == 0 || exitCode == 128) {
            return true;
        } else {
            return false;
        }
    }

    public static String getRepoName(String downloadUrl) {

        int ind = downloadUrl.lastIndexOf('/');
        StringBuilder sb = new StringBuilder();
        for (int i = ind + 1; i < downloadUrl.length() && downloadUrl.charAt(i) != '.'; i++) {
            sb.append(downloadUrl.charAt(i));
        }
        String folderName = sb.toString();
        return folderName;

    }
}
