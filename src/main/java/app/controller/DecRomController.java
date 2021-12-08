package app.controller;

import app.model.Converter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class DecRomController implements Initializable {
    Converter converter = new Converter();

    @FXML  private TextField TFDecimal;
    @FXML  private TextField TFRomain;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TFDecimal.setOnKeyReleased(event -> {
            String convert = converter.DecToRom(TFDecimal.getText());
            TFRomain.setText(convert);
        });

        TFRomain.setOnKeyReleased(event -> {
            int convert = converter.RomToDec(TFRomain.getText().toUpperCase(Locale.ROOT));
            TFDecimal.setText(String.valueOf(convert));
        });
    }
}
