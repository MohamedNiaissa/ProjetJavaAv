package app.controller;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MenuController {
    public void start(Stage primaryStage) throws Exception {
        final Menu menu1 = new Menu("File");
        final Menu menu2 = new Menu("Options");
        final Menu menu3 = new Menu("Help");

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menu1, menu2, menu3);
        menuBar.setUseSystemMenuBar(true);

        primaryStage.setTitle("Creating Menus with JavaFX 2.0");
        final Group rootGroup = new Group();
        final Scene scene = new Scene(rootGroup, 800, 400, Color.WHEAT);


        rootGroup.getChildren().add(menuBar);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
