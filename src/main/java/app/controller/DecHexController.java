package app.controller;

import app.model.Converter;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

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
            if(txtHexadecimal.getText().length()<8){
                if (txtHexadecimal.getText().matches("(?i)[0-9-abcdef]+")) {
                    String myDec = Converter.hexToDec(txtHexadecimal.getText());
                    txtDecimal.setText(myDec);
                    txtMessage.setText("verified : " + txtHexadecimal.getText() + " <=> " + Integer.toString(Integer.parseInt(myDec)));
                } else txtMessage.setText("Bad Number input: use only allowed chars.");
            }else {
                String prevMessage = txtMessage.getText();
                txtMessage.setText("Max Value reached, no more char available ...");
                txtHexadecimal.deletePreviousChar();
                txtMessage.setText(prevMessage);
            }
        });

        txtDecimal.setOnKeyReleased(event -> {

            if(txtDecimal.getText().length()<9){
                if(txtDecimal.getText().matches("[0-9]*")){
                    String hexaValue = Converter.decToHex(txtDecimal.getText());
                    txtHexadecimal.setText(hexaValue);
                    txtMessage.setText("verified : " + txtDecimal.getText() + " <=> " + Integer.toHexString(Integer.parseInt(txtDecimal.getText())));
                }else txtMessage.setText("Bad Number input: use only allowed chars.");
            } else {
                String prevMessage = txtMessage.getText();
                txtMessage.setText("Max Value reached, no more char available ...");
                txtDecimal.deletePreviousChar();
                txtMessage.setText(prevMessage);
            }
        });
    }
}
