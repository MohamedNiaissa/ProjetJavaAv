package app.controller;

import app.model.Converter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ImcController implements Initializable {
    Converter converter = new Converter();

    @FXML
    private Button btnValid;

    @FXML
    private Label lblCom;

    @FXML
    private TextField tfImc;

    @FXML
    private TextField tfPoids;

    @FXML
    private TextField tfTaille;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        btnValid.setOnMouseClicked(event ->{
            System.out.println(tfPoids.getText());
            System.out.println(tfTaille.getText());


            if (tfTaille.getText().isEmpty()){
                lblCom.setText("Veuillez entrer votre taille");

            }
            if (tfPoids.getText().isEmpty()) {
                lblCom.setText("Veuillez entrer votre poids");

            }
            converter.ImcResult(tfPoids.getText(), tfTaille.getText());

            if (!(tfPoids.getText().isEmpty() && tfTaille.getText().isEmpty())){

                String Imc = Float.toString(converter.ImcResult(tfPoids.getText(), tfTaille.getText()));
                tfImc.setText(Imc);

                if (converter.ImcResult(tfPoids.getText(), tfTaille.getText()) < 18.4){
                    lblCom.setText("Vous devez manger plus");
                }
                else if (converter.ImcResult(tfPoids.getText(), tfTaille.getText()) > 18.4 && converter.ImcResult(tfPoids.getText(), tfTaille.getText()) < 24.9){
                    lblCom.setText("Votre corpulence est normale");

                }
                else if (converter.ImcResult(tfPoids.getText(), tfTaille.getText()) > 25 && converter.ImcResult(tfPoids.getText(), tfTaille.getText()) < 29.9){
                    lblCom.setText("Vous etes en surpoids");

                }
                else if (converter.ImcResult(tfPoids.getText(), tfTaille.getText()) > 30 && converter.ImcResult(tfPoids.getText(), tfTaille.getText()) < 34.9){
                    lblCom.setText("Votre obésité est modérée");

                }
                else if (converter.ImcResult(tfPoids.getText(), tfTaille.getText()) > 35 && converter.ImcResult(tfPoids.getText(), tfTaille.getText()) < 39.9){
                    lblCom.setText("Votre obésité est sévère");

                }
                else if (converter.ImcResult(tfPoids.getText(), tfTaille.getText()) > 40){
                    lblCom.setText("ATTENTION = Obésité morbide");

                }

            }
        });


    }

}
