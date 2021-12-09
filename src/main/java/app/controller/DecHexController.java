package app.controller;

import app.model.Converter;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class DecHexController implements Initializable {
    @FXML
    private TextField txtDecimal;

    @FXML
    private TextField txtHexadecimal;

    @FXML
    private TextField txtMessage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        txtHexadecimal.setOnKeyReleased(event -> {
            if (txtHexadecimal.getText().matches("(?i)[0-9-abcdef]+")) {
                String myDec = Converter.hexToDec(txtHexadecimal.getText());
                txtDecimal.setText(myDec);
                txtMessage.setText("verified : " + txtHexadecimal.getText() + " <=> " + Integer.toString(Integer.parseInt(myDec)));
            } else txtMessage.setText("Bad Number input: use only allowed chars.");
        });

        txtDecimal.setOnKeyReleased(event -> {
            if(txtDecimal.getText().matches("[0-9]*")){
                String hexaValue = Converter.decToHex(txtDecimal.getText());
                txtHexadecimal.setText(hexaValue);
                txtMessage.setText("verified : " + txtDecimal.getText() + " <=> " + Integer.toHexString(Integer.parseInt(txtDecimal.getText())));
            }else txtMessage.setText("Bad Number input: use only allowed chars.");
        });
    }
}
