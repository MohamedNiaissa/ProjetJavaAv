package app.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class MainAppController implements Initializable {


    @FXML private VBox asideBib;
    @FXML private VBox pageBib;
    @FXML private VBox DecHexform;
    @FXML private VBox pageImc;
    @FXML private AnchorPane pageDecBin;
    @FXML private AnchorPane pageDecRom;
    @FXML private AnchorPane pageArmy;
    
    @FXML private MenuItem menuQuit;
    @FXML private MenuItem menuLib;
    @FXML private MenuItem menuBin;
    @FXML private MenuItem menuHexa;
    @FXML private MenuItem menuRoman;
    @FXML private MenuItem menuArmy;
    @FXML private MenuItem menuIMC;
    @FXML private MenuItem menuDark;
    @FXML private MenuItem menuLight;
    @FXML private VBox mainBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AtomicBoolean darktheme = new AtomicBoolean(false);
        AtomicInteger currentStory = new AtomicInteger();
        asideBib.getChildren().removeAll(pageBib, DecHexform, pageDecBin, pageImc, pageArmy, pageDecRom);

        /**
         * Quit the app
         */
        menuQuit.setOnAction(action -> {
            clearScenes(currentStory.get());
            System.exit(0);
        });

        /**
         * displays the Army setting story scene
         */
        menuArmy.setOnAction(action ->{
            if (currentStory.get() !=6) {
                clearScenes(currentStory.get());
                asideBib.getChildren().add(pageArmy); // ajouter la scene dédiée
                System.out.println(currentStory+" Army selected");
                currentStory.set(6);
            }
        });

        /**
         * displays the Binary converter scene
         */
        menuBin.setOnAction(action ->{
            if (currentStory.get() !=2) {
                clearScenes(currentStory.get());
                asideBib.getChildren().add(pageDecBin); // ajouter la scene dédiée
                System.out.println(currentStory+" Binary converter selected");
                currentStory.set(2);
            }
        });

        /**
         * displays the Hexadecimal converter scene
         */
        menuHexa.setOnAction(action ->{
            if (currentStory.get() !=3) {
                clearScenes(currentStory.get());
                asideBib.getChildren().add(DecHexform);
                System.out.println(currentStory+" Hexadecimal converter selected");
                currentStory.set(3);
            }
        });

        /**
         * displays the Roman converter scene
         */
        menuRoman.setOnAction(action ->{
            if (currentStory.get() !=4) {
                clearScenes(currentStory.get());
                asideBib.getChildren().add(pageDecRom); // ajouter la scene dédiée
                System.out.println(currentStory+" Roman converter selected");
                currentStory.set(4);
            }
        });

        /**
         * displays the IMC scene
         */
        menuIMC.setOnAction(action ->{
            if (currentStory.get() !=5) {
                clearScenes(currentStory.get());
                asideBib.getChildren().add(pageImc); // ajouter la scene dédiée
                System.out.println(currentStory+" IMC computing selected");
                currentStory.set(5);
            }
        });

        /**
         * display the Libray scene
         */
        menuLib.setOnAction(action ->{
            if (currentStory.get() !=1) {
                clearScenes(currentStory.get());
                asideBib.getChildren().add(pageBib);
                System.out.println(currentStory+" Library selected");
                currentStory.set(1);
            }
        });
    }

    /**
     * Remove the previous scene that was present before a menu choice
     * @param aScene current scene to be removed
     */
    public void clearScenes(int aScene){
        switch (aScene){
            case 1:asideBib.getChildren().remove(pageBib);
            case 2:asideBib.getChildren().remove(pageDecBin); // add the related FXML
            case 3:asideBib.getChildren().remove(DecHexform);
            case 4:asideBib.getChildren().remove(pageDecRom); // add the related FXML
            case 5:asideBib.getChildren().remove(pageImc); // add the related FXML
            case 6:asideBib.getChildren().remove(pageArmy); // add the related FXML
        }
    }
}
