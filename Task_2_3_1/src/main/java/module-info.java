module ru.nsu.stolyarov.task_2_3_1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens ru.nsu.stolyarov.task_2_3_1 to javafx.fxml;
    exports ru.nsu.stolyarov.task_2_3_1;
}