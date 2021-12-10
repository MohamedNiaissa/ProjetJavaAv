package app.controller;

import app.model.Converter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class DecRomController implements Initializable {
    Converter converter = new Converter();

    @FXML private TextField TFInteger;
    @FXML private TextField TFRoman;
    @FXML private Button btnValidity;
    @FXML private Label lblTitleIR;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblTitleIR.setStyle("-fx-background-color: #a29191");

        TFInteger.setOnKeyTyped(event -> {
            if(!TFInteger.getText().equals("")) {
                btnValidity.setText("Content Validity : No error detected.");
                TFRoman.setDisable(false);

                while(TFInteger.getText().length() > 5) { TFInteger.deletePreviousChar(); }
                String convert = converter.DecToRom(TFInteger.getText());

                if(convert.equals("InvalidCharacter")) {
                    btnValidity.setText("Content Validity : Integer Error -> The conversion only support numerical values.");
                    convert = "Error";
                    TFRoman.setDisable(true);

                } else if(convert.equals("InvalidNumber")) {
                    btnValidity.setText("Content Validity : Integer Error -> The input number exceeded the capacity of the converter.");
                    convert = "Error";
                    TFRoman.setDisable(true);
                }
                TFRoman.setText(convert);

            } else {
                btnValidity.setText("Content Validity : No error detected.");
                TFRoman.clear();
                TFRoman.setDisable(false);
            }
        });

        TFRoman.setOnKeyTyped(event -> {
            if(!TFRoman.getText().equals("")) {
                btnValidity.setText("Content Validity : No error detected.");
                TFInteger.setDisable(false);

                while(TFRoman.getText().length() > 10) { TFRoman.deletePreviousChar(); }
                System.out.println(TFRoman.getText().toUpperCase(Locale.ROOT));
                String convert = converter.RomToDec(TFRoman.getText().toUpperCase(Locale.ROOT));
                String verify = converter.DecToRom(convert);

                if(convert.equals("InvalidNumber") || verify.equals("InvalidNumber")) {
                    btnValidity.setText("Content Validity : Roman Error -> The input number exceed the capacity of the converter.");
                    convert = "Error";
                    TFInteger.setDisable(true);
                } else if(convert.equals("InvalidCharacter") || verify.equals("InvalidCharacter")) {
                    btnValidity.setText("Content Validity : Roman Error -> The conversion only support I-V-X-L-C-D-M values.");
                    convert = "Error";
                    TFInteger.setDisable(true);

                } else if (!Objects.equals(verify, TFRoman.getText().toUpperCase(Locale.ROOT))) {
                    btnValidity.setText("Content Validity : Roman Error -> This roman number doesn't exist.");
                    convert = "Error";
                    TFInteger.setDisable(true);

                }
                TFInteger.setText(convert);

            } else {
                btnValidity.setText("Content Validity : No error detected.");
                TFInteger.clear();
                TFInteger.setDisable(false);
            }
        });
    }
}
