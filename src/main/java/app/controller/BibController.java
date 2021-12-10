package app.controller;

import app.model.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.*;

public class BibController implements Initializable {

    @FXML private TextField champColonne;
    @FXML private TextField champName;
    @FXML private TextField champAuteur;
    @FXML private TextField champParution;
    @FXML private TextField champRangee;
    @FXML private TextArea champResume;
    @FXML private TableColumn<Book, String> auteur;
    @FXML private TableColumn<Book, String> colonne;
    @FXML private TableColumn<Book, String> name;
    @FXML private TableColumn<Book,String> parution;
    @FXML private TableColumn<Book, String> rangee;
    @FXML private TableColumn<Book, String> resume;
    @FXML private TableView<Book> tabBib;
    @FXML private Button plus;
    @FXML private TextField urlImage;
    @FXML private ImageView imgView;
    @FXML private Button btnValider;
    @FXML private VBox formumaire;
    @FXML private HBox contentMain;
    @FXML private Button suppr;
    @FXML private Label txtNotFound;
    @FXML private Label txtError;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnValider.setDisable(true);
        Date date = new Date();
        Calendar annee = Calendar.getInstance();
        int anneeActuel = annee.get(Calendar.YEAR);

        contentMain.getChildren().remove(formumaire);

        /**
         * Condition concernagt la validité des champs obligatoire
         */
        champParution.setOnKeyTyped(keyTyped -> {

            if (!Objects.equals(champParution.getText(), "")) {
                txtError.setText("");
                btnValider.setDisable(false);

                if (champParution.getText().length() > 5) {
                    txtError.setText(" Vous ne pouvez pas dépasser \n4 numeros ");
                    champParution.deletePreviousChar();
                    btnValider.setDisable(true);

                } else if (!champParution.getText().matches("[0-9]*")) {
                    txtError.setText("Numero attendu dans le \nchamp Parution");
                    btnValider.setDisable(true);

                } else if (Integer.parseInt(champParution.getText()) > anneeActuel) {
                    txtError.setText("Date non valide");
                    btnValider.setDisable(true);
                }

            } else {
                btnValider.setDisable(false);
            }
        });

        champName.setOnKeyTyped(keyTyped -> {
            if (!Objects.equals(champName.getText(),"")) {
                txtError.setText("");
                btnValider.setDisable(false);

                if (champName.getText().length() > 21) {
                    txtError.setText("Maximum 20 caracteres.");
                    champName.deletePreviousChar();
                    btnValider.setDisable(true);

                } else if (!champName.getText().matches("[a-zA-Z]*")) {
                    txtError.setText("  Erreur : \ncaractère uniquement");
                    btnValider.setDisable(true);
                }
            } else {
                txtError.setText("");
                btnValider.setDisable(false);
            }
        });

        champAuteur.setOnKeyTyped(keyTyped -> {
            if (!Objects.equals(champAuteur.getText(),"")) {

                txtError.setText("");
                btnValider.setDisable(false);

                if (champName.getText().length() > 21) {
                    txtError.setText("Maximum 20 caracteres.");
                    champAuteur.deletePreviousChar();
                    btnValider.setDisable(true);

                } else if (!champAuteur.getText().matches("[a-zA-Z]*")) {
                    txtError.setText("  Erreur : \ncaractère uniquement");
                    btnValider.setDisable(true);
                }
            } else {
                txtError.setText("");
                btnValider.setDisable(false);
            }
        });


        champColonne.setOnKeyTyped(keyTyped -> {


            if (!Objects.equals(champColonne.getText(),"")) {
                txtError.setText("");
                btnValider.setDisable(false);

                if (!champColonne.getText().matches("[0-9]*")) {
                    txtError.setText("Veuillez insérer uniquement un numero \npour les colonnes");
                    btnValider.setDisable(true);
                }else if (champColonne.getText().length() > 2) {
                    txtError.setText("Veuillez insérer une colonne comprise entre 1 et 7");
                    champParution.deletePreviousChar();
                    btnValider.setDisable(true);
                }  else if (Integer.parseInt(champColonne.getText()) > 7 || Integer.parseInt(champColonne.getText()) < 1) {
                    txtError.setText("Veuillez insérer une colonne comprise entre 1 et 7");
                    btnValider.setDisable(true);
                }

            } else {
                txtError.setText("");
                btnValider.setDisable(false);
            }
        });

        champRangee.setOnKeyTyped(keyTyped -> {
            if (!Objects.equals(champRangee.getText(), "")) {
                txtError.setText("");
                btnValider.setDisable(false);


                if (champRangee.getText().length() > 2) {
                    txtError.setText("Veuillez insérer une rangée comprise entre 1 et 5");
                    champParution.deletePreviousChar();
                    btnValider.setDisable(true);

                } else if (!champRangee.getText().matches("[0-9]*")) {
                    txtError.setText("Veuillez insérer uniquement un numero \npour le champ Rangée");
                    btnValider.setDisable(true);

                } else if (Integer.parseInt(champRangee.getText()) > 5 || Integer.parseInt(champRangee.getText()) < 1) {
                    txtError.setText("Veuillez insérer une rangée \ncomprise entre 1 et 5");
                    btnValider.setDisable(true);
                }
            } else {
                txtError.setText("");
                btnValider.setDisable(false);
            }
        });



        /* List vue par JavaFX*/
        ObservableList<Book> books = FXCollections.observableArrayList();

        // affichage de l'image en fonction de chacune des touches tapées dans le champ url
        urlImage.setOnKeyReleased(event -> {
            if (!"".equals(urlImage.getText())) displayImage(urlImage.getText());
        });

        /**
         * Evenement qui se déroule dès que l'on clique sur le bourton plus
         */
        plus.setOnMouseClicked(apparitionForm -> {


            txtError.setText("");
            unDisplayImage();

            //Verification de l'existance ou pas du formulaire, si present dans ce cas là pas de création par dessus sinon on le crée
            if (!contentMain.getChildren().contains(formumaire)) {
                contentMain.getChildren().add(formumaire);
            }

            // Evenement quand on clique d'abord sur le bouton plus pui s valider
            btnValider.setOnMouseClicked(addBook -> {

                boolean bool = true;

                unDisplayImage(); // On retire l'image de son champ à chaque fois que l'on click sur un élément du tableau
                String repName = champName.getText();
                String repAuteur = champAuteur.getText();
                String repResume = champResume.getText();
                String repurlImage = urlImage.getText();
                int repColonne;
                int repRangee;
                int repParution;

                // Prévention des erreurs de conversion de String en entier pour les champs correspondants, nottament si le champ est vide
                try {
                    repColonne = Integer.parseInt(champColonne.getText());
                }catch (NumberFormatException ignore) {
                    txtError.setText("Probleme: \nchamp Colonne");
                    return;

                }
                try {
                    repRangee = Integer.parseInt(champRangee.getText());
                }catch (NumberFormatException ignore) {
                    txtError.setText("Probleme: \nchamp Rangée");
                    return;
                }

                try {
                    repParution = Integer.parseInt(champParution.getText());
                }catch (NumberFormatException ignore) {
                    txtError.setText("Probleme: \nchamp Parution");
                    return;
                }

                // Verification de tout les champs si ils sont vides
                boolean conditionAdd = (!repName.equals("") && !repAuteur.equals("") && !Integer.toString(repColonne).equals("") && !Integer.toString(repRangee).equals("") && !Integer.toString(repParution).equals(""));

                // definition des différentes colonnes
                name.setCellValueFactory(new PropertyValueFactory<Book, String>("name"));
                auteur.setCellValueFactory(new PropertyValueFactory<Book, String>("auteur"));
                colonne.setCellValueFactory(new PropertyValueFactory<Book, String>("colonne"));
                rangee.setCellValueFactory(new PropertyValueFactory<Book, String>("rangee"));
                resume.setCellValueFactory(new PropertyValueFactory<Book, String>("resume"));
                parution.setCellValueFactory(new PropertyValueFactory<Book, String>("parution"));
                tabBib.setItems(books);

                // Ajout du livre dans la bibliotheque si la condition est verifie
                if (conditionAdd){
                    Book myBook = new Book(repName, repAuteur, repResume, repColonne, repRangee, repParution, repurlImage);

                    txtError.setText("");
                    books.add(myBook);
                    Clear();
                    contentMain.getChildren().remove(formumaire);
                }else {
                    txtError.setText("Veuillez renseigner le/les \nchamps manquants svp");
                }


            });
        });

        suppr.setOnMouseClicked(supprLivre -> {

            if (tabBib.getItems().size() == 0) {
                txtError.setText("Aucun élément à supprimer");
                return;
            } else {
                if (tabBib.getItems().size() == 1) {  // supprime le formulaire des que le dernier book est supprimé
                    contentMain.getChildren().remove(formumaire);
                }
                try {
                    TablePosition selectCellSupr = tabBib.getSelectionModel().getSelectedCells().get(0);
                    books.remove(selectCellSupr.getRow());
                    Clear(); // Vider contenu du champs apres utilisation
                    txtError.setText("");
                    return;
                } catch (Exception ignore) {}
            }
            txtError.setText("Veuillez selectionner \nune case");
        });

        /* Voir les informations d'un livre */
        tabBib.setOnMouseClicked(selectChamp -> {
            unDisplayImage();

            ObservableList<Book> selectedItems = tabBib.getSelectionModel().getSelectedItems();

            if (selectedItems.size() != 0) {
                /**
                 * Condition de verification concernant l'existance des elements dans le tableau
                 */
                TablePosition selectCell = tabBib.getSelectionModel().getSelectedCells().get(0);

                champName.setText(selectedItems.get(0).getName());
                champResume.setText(selectedItems.get(0).getResume());
                champParution.setText(Integer.toString(selectedItems.get(0).getParution()));
                champAuteur.setText(selectedItems.get(0).getAuteur());
                champColonne.setText(Integer.toString(selectedItems.get(0).getColonne()));
                champRangee.setText(Integer.toString(selectedItems.get(0).getRangee()));
                urlImage.setText(selectedItems.get(0).getUrl());
                if (!"".equals(urlImage.getText())) displayImage(urlImage.getText());

                if (!contentMain.getChildren().contains(formumaire)) {
                    contentMain.getChildren().add(formumaire);
                }

                btnValider.setOnMouseClicked(modifier1 -> {
                    String repName = champName.getText();
                    String repAuteur = champAuteur.getText();
                    String repResume = champResume.getText();
                    String repurlImage = urlImage.getText();
                    int repColonne;
                    int repRangee;
                    int repParution;

                    try {
                        repColonne = Integer.parseInt(champColonne.getText());
                    }catch (NumberFormatException ignore) {
                        txtError.setText("Probleme: \nchamp Colonne");
                        return;

                    }
                    try {
                        repRangee = Integer.parseInt(champRangee.getText());
                    }catch (NumberFormatException ignore) {
                        txtError.setText("Probleme: \nchamp Rangée");
                        return;
                    }

                    try {
                        repParution = Integer.parseInt(champParution.getText());
                    }catch (NumberFormatException ignore) {
                        txtError.setText("Probleme: \nchamp Parution");
                        return;
                    }


                    Book modifyBook = new Book(repName, repAuteur, repResume, repColonne, repRangee, repParution, urlImage.getText());
                    books.set(selectCell.getRow(), modifyBook);  // modification du livre choisi
                    Clear();
                    contentMain.getChildren().remove(formumaire); // On retire le formulaire à chaque fois que l'on termine de l'utiliser
                });

            }

        });
    }
    /**
     * remove the image from the imageView
     */
    private void unDisplayImage() {
            imgView.imageProperty().set(null);
    }
    /**
     * displays linked image of a book in the form
     * @param imgUrl URL of image
     */
    private void displayImage(String imgUrl) {
        Image myImage = null;
        boolean backgroundLoading = false;
        String imgName = "" + imgUrl;
                txtNotFound.setText(" ");
                imgView.imageProperty().set(null);
                try {
                    myImage = new Image(imgUrl, backgroundLoading);
                    imgView.setImage(myImage);
                    txtNotFound.setText("");
                } catch(Exception e) {
                    System.out.println("image not found");
                    txtNotFound.setText("");
                    txtNotFound.setText("not found");
                }
        }



        private void Clear(){
            /**
             *  Effacement des champs après validation
             */
            champColonne.clear();
            champAuteur.clear();
            champParution.clear();
            champRangee.clear();
            champName.clear();
            champResume.clear();
            urlImage.clear();
        }


    }
