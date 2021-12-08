package app.controller;

import app.model.Converter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DecHexController implements Initializable {
    @FXML
    private TextField txtDecimal;

    @FXML
    private TextField txtHexadecimal;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtHexadecimal.selectionProperty().addListener(observable -> {
            int test = Converter.hexToDec(txtHexadecimal.getText());
            txtDecimal.setText(Integer.toString(test));
        });

        txtDecimal.selectionProperty().addListener(observable -> {
            String hexaValue = Converter.decToHex(txtDecimal.getText());
            txtHexadecimal.setText(hexaValue);
        });

    }

}
