package app.controller;

import app.model.Converter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

public class ImcController implements Initializable {
    Converter converter = new Converter();

    @FXML private Slider sliderImc;
    @FXML private Button btnValid;
    @FXML private Label lblCom;
    @FXML private TextField tfImc;
    @FXML private TextField tfPoids;
    @FXML private TextField tfTaille;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AtomicBoolean weightOK = new AtomicBoolean(false);
        AtomicBoolean sizeOK = new AtomicBoolean(false);

        /*definition du slider*/
        sliderImc.setMin(0);
        sliderImc.setMax(12);
        sliderImc.setShowTickMarks(true);

        /* Check si conditions remplies pour valider la saisi */
        tfPoids.setOnKeyReleased (eventP -> {
            if (tfPoids.getText().matches("[0-9.]*") && tfPoids.getText().length()<= 3){
                weightOK.set(true);
                lblCom.setText("");
            }
            else if (tfPoids.getText().length() > 3) {
                weightOK.set(false);
                tfPoids.deletePreviousChar();
                lblCom.setText("Limite de 3 chiffres atteinte");
            }
            else {
                weightOK.set(false);
                System.out.println("Illegal character.");
                lblCom.setText("Veuillez entrer un poids valide");
            }
        });
        tfTaille.setOnKeyReleased(eventT ->{
            if (tfTaille.getText().matches("[0-9.]*") && tfTaille.getText().length() <=4) {
                sizeOK.set(true);
                lblCom.setText("");
            }

            else if (tfTaille.getText().length()>4){
                sizeOK.set(false);
                tfTaille.deletePreviousChar();
                lblCom.setText("Limite de 4 caracteres atteinte");
            }

            else {
                sizeOK.set(false);
                System.out.println("Illegal character.");
                lblCom.setText("Veuillez entrer une taille valide");
            }

        });

        btnValid.setOnMouseClicked(event ->{

        /*Check si textfields sont vides */

          if (tfTaille.getText().isEmpty()){
              lblCom.setText("Veuillez entrer votre taille");
              sizeOK.set(false);

            }
          if (tfPoids.getText().isEmpty()) {
              lblCom.setText("Veuillez entrer votre poids");
              sizeOK.set(false);

            }

            /* Si NON Vide */
//
            if(sizeOK.get() && weightOK.get()){
                float imcF = converter.ImcResult(tfPoids.getText(), tfTaille.getText());
                DecimalFormat df = new DecimalFormat("#######.##");
                String imcS = df.format(imcF);
                tfImc.setText(imcS);

                sliderImc.setValue(imcF);

                if (imcF < 18.4){
                    lblCom.setText("Vous devez manger plus");
                    sliderImc.setValue(1);
                }
                else if (imcF >= 18.4 && imcF <= 25){
                    lblCom.setText("Votre corpulence est normale");
                    sliderImc.setValue(3);
                }
                else if (imcF > 25 && imcF <= 30){
                    lblCom.setText("Vous etes en surpoids");
                    sliderImc.setValue(5);
                }
                else if (imcF > 30 && imcF <= 35){
                    lblCom.setText("Vous etes en faible obésité");
                    sliderImc.setValue(7);
                }
                else if (imcF > 35 && imcF <= 40){
                    lblCom.setText("Vous etes en obésité moyenne");
                    sliderImc.setValue(9);
                }
                else {
                    lblCom.setText("Vous etes en obésité sévère");
                    sliderImc.setValue(11);
                }

            }
        });


    }

}
