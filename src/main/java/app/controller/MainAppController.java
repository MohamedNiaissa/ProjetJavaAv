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

    }
}
