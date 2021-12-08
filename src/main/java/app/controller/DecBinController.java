package app.controller;

import app.model.Converter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class DecBinController implements Initializable {
    Converter converter = new Converter();

    @FXML private TextField TFDecimalB;
    @FXML  private TextField TFBinaire;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TFDecimalB.setOnKeyReleased(event -> {
            if(!TFDecimalB.getText().isBlank()) {
                String convert = converter.DecToBin(TFDecimalB.getText());
                TFBinaire.setText(convert);
            }
        });

        TFBinaire.setOnKeyReleased(event -> {
            if(!TFBinaire.getText().isBlank()) {
                int convert = converter.BinToDec(TFBinaire.getText());
                TFDecimalB.setText(String.valueOf(convert));
            }
        });
    }
}