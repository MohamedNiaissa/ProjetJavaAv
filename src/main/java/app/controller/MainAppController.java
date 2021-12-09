package app.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MainAppController implements Initializable {
    @FXML
    private Button btnBib;

    @FXML
    private Button btnDecHex;

    @FXML
    private VBox asideBib;

    @FXML
    private VBox pageBib;

    @FXML
    private VBox DecHexform;

    @FXML
    private MenuItem menuClose;

    @FXML
    private MenuItem menuQuit;

    @FXML
    private MenuItem menuLib;

    @FXML
    private MenuItem menuBin;

    @FXML
    private MenuItem menuHexa;

    @FXML
    private MenuItem menuRoman;

    @FXML
    private MenuItem menuArmy;

    @FXML
    private MenuItem menuIMC;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        asideBib.getChildren().removeAll(pageBib, DecHexform);

        btnBib.setOnMouseClicked( btnAction -> {
            asideBib.getChildren().add(pageBib);
        });

        menuClose.setOnAction(action -> {
            System.exit(0);
        });

        menuQuit.setOnAction(action -> {
            System.exit(0);
        });

        menuArmy.setOnAction(action ->{
            System.out.println("Army selected");
        });

        menuBin.setOnAction(action ->{
            System.out.println("binary converter selected");
        });

        menuHexa.setOnAction(action ->{
            System.out.println("headecimal converter selected");
        });

        menuRoman.setOnAction(action ->{
            System.out.println("Roman converter selected");
        });

        menuIMC.setOnAction(action ->{
            System.out.println("IMC computing selected");
        });

        menuLib.setOnAction(action ->{
            System.out.println("Army selected");
        });

    }
}
