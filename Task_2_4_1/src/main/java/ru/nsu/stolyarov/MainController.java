package ru.nsu.stolyarov;

import java.io.IOException;

public class MainController {
    public static void act(String downloadUrl) throws IOException, InterruptedException {
        if(RepoManager.repoDownload(downloadUrl)){
            String folderName = RepoManager.getRepoName(downloadUrl);
            Task task = TaskChecker.processTask(folderName, "Task_1_1_1");
            HtmlManager.printRes(task);
        }
    }
}
