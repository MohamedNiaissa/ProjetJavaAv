module app.projetjavaav {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens app to javafx.fxml;
    exports app;
    exports app.controller;
    opens app.controller to javafx.fxml;
}