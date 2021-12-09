package app.controller;

import app.model.Converter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

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
        AtomicBoolean weightOK = new AtomicBoolean(false);
        AtomicBoolean sizeOK = new AtomicBoolean(false);


        tfPoids.setOnKeyReleased(eventP -> {
            if (tfPoids.getText().matches("[0-9.]*") && tfPoids.getText().length()<= 3) {
                weightOK.set(true);
                lblCom.setText("");
            }
            else if (tfPoids.getText().length() > 3) {
                weightOK.set(false);
                tfPoids.deletePreviousChar();
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
            }

            else {
                sizeOK.set(false);
                System.out.println("Illegal character.");
                lblCom.setText("Veuillez entrer une taille valide");
            }

        });

        btnValid.setOnMouseClicked(event ->{
            System.out.println(tfPoids.getText());
            System.out.println(tfTaille.getText());

        /*Check si textfields sont vides */

          if (tfTaille.getText().isEmpty()){
                lblCom.setText("Veuillez entrer votre taille");

            }
          if (tfPoids.getText().isEmpty()) {
                lblCom.setText("Veuillez entrer votre poids");

            }

            /* Si NON Vide */
//
            if(sizeOK.get() && weightOK.get()){
                float imcF = converter.ImcResult(tfPoids.getText(), tfTaille.getText());
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
