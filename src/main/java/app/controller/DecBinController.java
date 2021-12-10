package app.controller;

import app.model.Converter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DecBinController implements Initializable {
    Converter converter = new Converter();

    @FXML private TextField TFDecimalB;
    @FXML private TextField TFBinaire;
    @FXML private Label lblTitleIB;
    @FXML private Button btnValidity;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblTitleIB.setStyle("-fx-background-color: #a29191");

        TFDecimalB.setOnKeyTyped(event -> {
            if(!TFDecimalB.getText().equals("")) {
                btnValidity.setText("Content Validity : No error detected.");
                TFBinaire.setDisable(false);

                if (TFDecimalB.getText().length() > 12) TFDecimalB.deletePreviousChar();
                String convert = converter.DecToBin(TFDecimalB.getText());

                if(convert.equals("InvalidCharacter")) {
                    btnValidity.setText("Content Validity : Integer Error -> The conversion only support numerical values.");
                    convert = "Error";
                    TFBinaire.setDisable(true);

                } else if(convert.equals("InvalidNumber")) {
                    btnValidity.setText("Content Validity : Integer Error -> The input number exceed the capacity of the converter.");
                    convert = "Error";
                    TFBinaire.setDisable(true);
                }
                TFBinaire.setText(convert);

            } else {
                btnValidity.setText("Content Validity : No error detected.");
                TFBinaire.clear();
                TFBinaire.setDisable(false);
            }
        });

        TFBinaire.setOnKeyTyped(event -> {
            if(!TFBinaire.getText().equals("")) {
                btnValidity.setText("Content Validity : No error detected.");
                TFDecimalB.setDisable(false);

                if (TFBinaire.getText().length() > 32) TFBinaire.deletePreviousChar();
                String convert = converter.BinToDec(TFBinaire.getText());
                TFDecimalB.setText(String.valueOf(convert));
                String verify = converter.DecToBin(TFDecimalB.getText());

                if(convert.equals("InvalidCharacter")) {
                    btnValidity.setText("Content Validity : Binary Error -> The conversion only support numerical values.");
                    convert = "Error";
                    TFDecimalB.setDisable(true);

                } else if(convert.equals("InvalidBinary")) {
                    btnValidity.setText("Content Validity : Binary Error -> The input number can only be 1 or 0.");
                    convert = "Error";
                    TFDecimalB.setDisable(true);

                } else if(convert.equals("InvalidNumber") || verify.equals("InvalidNumber")) {
                    btnValidity.setText("Content Validity : Integer Error -> The input number exceed the capacity of the converter.");
                    convert = "Error";
                    TFDecimalB.setDisable(true);
                }
                TFDecimalB.setText(convert);

            } else {
                btnValidity.setText("Content Validity : No error detected.");
                TFDecimalB.clear();
                TFDecimalB.setDisable(false);
            }
        });
    }
}