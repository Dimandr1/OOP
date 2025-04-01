package ru.nsu.stolyarov.task_2_3_1;

import javafx.scene.layout.GridPane;

public class ViewManager {
    private GridPane field;
    private int width;
    private int height;

    public ViewManager(GridPane field, int width, int height){
        this.field = field;
        this.width = width;
        this.height = height;
    }
}
