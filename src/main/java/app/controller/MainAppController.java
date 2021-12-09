package app.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

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
        AtomicInteger currentStory = new AtomicInteger();
        asideBib.getChildren().removeAll(pageBib, DecHexform); // add all children at the beginning

/**
 *             btnBib.setOnMouseClicked( btnAction -> {
 *                 asideBib.getChildren().add(pageBib);
 *             });
 */

        menuClose.setOnAction(action -> {
            System.exit(0);
        });

        menuQuit.setOnAction(action -> {
            System.exit(0);
        });

        menuArmy.setOnAction(action ->{
            if (currentStory.get() !=6) {
//                asideBib.getChildren().add(); / ajouter la scene dédiée
                System.out.println(currentStory+" Army selected");
                currentStory.set(6);
            }
        });

        menuBin.setOnAction(action ->{
            if (currentStory.get() !=2) {
//                asideBib.getChildren().add(); / ajouter la scene dédiée
                System.out.println(currentStory+" Binary converter selected");
                currentStory.set(2);
            }
        });

        menuHexa.setOnAction(action ->{
            if (currentStory.get() !=3) {
                clearScenes(currentStory.get());
                asideBib.resize(245,125);
                asideBib.getChildren().add(DecHexform);
                System.out.println(currentStory+" Hexadecimal converter selected");
                currentStory.set(3);
            }
        });

        menuRoman.setOnAction(action ->{
            if (currentStory.get() !=4) {
//                asideBib.getChildren().add(); / ajouter la scene dédiée
                System.out.println(currentStory+" Roman converter selected");
                currentStory.set(4);
            }
        });

        menuIMC.setOnAction(action ->{
            if (currentStory.get() !=5) {
//                asideBib.getChildren().add(); / ajouter la scene dédiée
                System.out.println(currentStory+" IMC computing selected");
                currentStory.set(5);
            }
        });

        menuLib.setOnAction(action ->{
            if (currentStory.get() !=1) {
                clearScenes(currentStory.get());
                asideBib.resize(800,600);
                asideBib.getChildren().add(pageBib);
                System.out.println(currentStory+" Library selected");
                currentStory.set(1);
            }
        });
    }

    public void clearScenes(int aScene){
        switch (aScene){
            case 1:asideBib.getChildren().remove(pageBib);
            case 2:asideBib.getChildren().remove(pageBib);
            case 3:asideBib.getChildren().remove(DecHexform);
            case 4:asideBib.getChildren().remove(pageBib);
            case 5:asideBib.getChildren().remove(pageBib);
            case 6:asideBib.getChildren().remove(pageBib);

        }
    }
}
