package app.controller;

import app.model.Converter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class DecRomController implements Initializable {
    Converter converter = new Converter();

    @FXML  private TextField TFDecimal;
    @FXML  private TextField TFRomain;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TFDecimal.setOnKeyReleased(event -> {
            if(!TFDecimal.getText().isBlank()) {
                String convert = converter.DecToRom(TFDecimal.getText());
                TFRomain.setText(convert);
            }
        });

        TFRomain.setOnKeyReleased(event -> {
            if(!TFRomain.getText().isBlank()) {
                int convert = converter.RomToDec(TFRomain.getText().toUpperCase(Locale.ROOT));
                String verify = converter.DecToRom(String.valueOf(convert));
                if (Objects.equals(verify, TFRomain.getText())) TFDecimal.setText(String.valueOf(convert));
                else System.out.println("Wrong format");
            }
        });
    }
}
