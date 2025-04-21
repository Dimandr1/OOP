package ru.nsu.stolyarov;

import java.io.IOException;
import java.util.ArrayList;

public class MainController {
    private ArrayList<Task> actOneRepo(String downloadUrl, ArrayList<String> taskNames) throws IOException, InterruptedException {
        ArrayList<Task> tasks = new ArrayList<>();
        if (RepoManager.repoDownload(downloadUrl)) {
            String folderName = RepoManager.getRepoName(downloadUrl);
            for(int i = 0; i < taskNames.size(); i++){
                Task task = TaskChecker.processTask(downloadUrl, folderName, taskNames.get(i));
                tasks.add(task);
            }
        }
        return tasks;
    }

    public void act(ArrayList<String> repoUrls, ArrayList<ArrayList<String>> taskNames) throws IOException, InterruptedException {
        ArrayList<Task> res = new ArrayList<>();
        for(int i = 0; i < repoUrls.size(); i++){
            res.addAll(actOneRepo(repoUrls.get(i), taskNames.get(i)));
        }
        HtmlManager.printRes(res);
    }
}
