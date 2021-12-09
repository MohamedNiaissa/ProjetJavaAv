package app.controller;

import app.model.Converter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
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

        /* creation arrayList pour decomposer ce qui est tapé puis supprimer lettres et remplacer , par . ou try catch*/
        tfPoids.setOnKeyReleased(eventP -> {
            if (tfPoids.getText().matches("[0-9.]*")) {
                if (tfPoids.getText().length() > 3) tfPoids.deletePreviousChar();
            }else {
                System.out.println("Illegal character.");
                //if (tfPoids.getText().length() > 3) tfPoids.;
            }
        });
        tfTaille.setOnKeyReleased(eventT ->{
            if (tfTaille.getText().matches("[0-9.]*")) {
                if (tfTaille.getText().length() > 5) tfPoids.deletePreviousChar();
            } else System.out.println("Illegal character.");

        });

        btnValid.setOnMouseClicked(event ->{
            System.out.println(tfPoids.getText());
            System.out.println(tfTaille.getText());

        /*Check si textfields sont vides */

            if (tfTaille.getText().isEmpty() || tfPoids.getText().isEmpty()) {
                lblCom.setText("Veuillez entrer votre taille et/ou poids");
            }
          /* if (tfTaille.getText().isEmpty()){
                lblCom.setText("Veuillez entrer votre taille");

            }
            if (tfPoids.getText().isEmpty()) {
                lblCom.setText("Veuillez entrer votre poids");

            }*/

            converter.ImcResult(tfPoids.getText(), tfTaille.getText());
            float imcF = converter.ImcResult(tfPoids.getText(),tfTaille.getText());


            /* Si NON Vide */
            if (!(tfPoids.getText().isEmpty() && tfTaille.getText().isEmpty())){

                DecimalFormat df = new DecimalFormat("#######.##");
                String imcS = df.format(imcF);
                tfImc.setText(imcS);

                if (imcF < 18.4){
                    lblCom.setText("Vous devez manger plus");
                }
                else if (imcF > 18.4 && imcF < 24.9){
                    lblCom.setText("Votre corpulence est normale");

                }
                else if (imcF > 25 && imcF < 29.9){
                    lblCom.setText("Vous etes en surpoids");

                }
                else if (imcF > 30 && imcF < 34.9){
                    lblCom.setText("Votre obésité est modérée");

                }
                else if (imcF > 35 && imcF < 39.9){
                    lblCom.setText("Votre obésité est sévère");

                }
                else {
                    lblCom.setText("ATTENTION = Obésité morbide");

                }

            }
        });


    }

}
