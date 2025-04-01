package ru.nsu.stolyarov.task_2_3_1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import ru.nsu.stolyarov.task_2_3_1.Model.GameManager;
import ru.nsu.stolyarov.task_2_3_1.Model.Settings;

import java.io.IOException;

public class Controller extends Application {

    public Button startButton;
    public TextField widthInput;
    public TextField heightInput;
    public TextField foodAmountInput;
    public Text errorText;
    @FXML
    public void startButtonPressed(ActionEvent event) throws IOException {
        if(!checkSettingsCorrectness()){
            errorText.setVisible(true);
            return;
        }
        errorText.setVisible(false);
        Settings settings = new Settings(Integer.parseInt(widthInput.getText()),
                Integer.parseInt(heightInput.getText()),
                Integer.parseInt(foodAmountInput.getText()));
        Parent root = FXMLLoader.load(getClass().getResource("scene2.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
        GameManager game = new GameManager();
        game.startGame(settings);
    }

    private boolean checkSettingsCorrectness(){
        if(!isNatural(widthInput.getText())){
            return false;
        }
        if(!isNatural(heightInput.getText())){
            return false;
        }
        if(!isNatural(foodAmountInput.getText())){
            return false;
        }
        return true;
    }
    private boolean isNatural(String str) {
        try {
            if(Integer.parseInt(str) <= 0){
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Controller.class.getResource("sample.fxml"));
    }

    public static void main(String[] args) {
        launch();
    }
}
