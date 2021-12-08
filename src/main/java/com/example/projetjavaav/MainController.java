package com.example.projetjavaav;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Button btnBib;

    @FXML
    private VBox asideBib;

    @FXML
    private VBox pageBib;

    @FXML
    private Menu menuClose;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        asideBib.getChildren().remove(pageBib);

        btnBib.setOnMouseClicked( btnAction -> {

            asideBib.getChildren().add(pageBib);

        });

        menuClose.setOnAction(action -> {
            System.exit(0);
        });

    }
}
