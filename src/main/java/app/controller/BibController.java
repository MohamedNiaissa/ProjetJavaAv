package app.controller;

import app.model.Biblio;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class BibController implements Initializable {
    Biblio biblio = new Biblio();



    @FXML
    private TextField champColonne;

    @FXML
    private TextField champName;

    @FXML
    private TextField champAuteur;

    @FXML
    private TextField champParution;

    @FXML
    private TextField champRangee;

    @FXML
    private TextField champResume;
    @FXML
    private TableColumn<Book, String> auteur;

    @FXML
    private TableColumn<Book, String> colonne;

    @FXML
    private TableColumn<Book, String> name;

    @FXML
    private TableColumn<Book,String> parution;

    @FXML
    private TableColumn<Book, String> rangee;

    @FXML
    private TableColumn<Book, String> resume;

    @FXML
    private TableView<Book> tabBib;


    @FXML
    private Button plus;

    @FXML
    private TextField urlImage;

    @FXML
    private ImageView imgView;

    @FXML
    private Button btnValider;

    @FXML
    private VBox formumaire;

    @FXML
    private VBox content;

    @FXML
    private HBox contentMain;

    @FXML
    private Button suppr;

    @FXML
    private Label txtNotFound;


    @FXML
    private Label txtError;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ArrayList<TextField> champs = new ArrayList();
        champs.add(champName);
        champs.add(champColonne);
        champs.add(champRangee);
        champs.add(champParution);
        champs.add(champAuteur);

        Date date = new Date();
        Calendar annee = Calendar.getInstance();
        int anneeActuel = annee.get(Calendar.YEAR);

        contentMain.getChildren().remove(formumaire);


        /* List vue par JavaFX*/
        ObservableList<Book> books = FXCollections.observableArrayList();

        urlImage.setOnKeyReleased(event -> {
            if(!"".equals(urlImage.getText())) displayImage(urlImage.getText());
        });
        plus.setOnMouseClicked(apparitionForm -> {

            txtError.setText("");
            unDisplayImage();
            if (!contentMain.getChildren().contains(formumaire)){
                contentMain.getChildren().add(formumaire);
            }

            btnValider.setOnMouseClicked(addBook -> {


                for (int i = 0; i < champs.size(); i++) {
                    if (champs.get(i).getText().equals("")){
                        txtError.setText("Veuillez remplir le/les champs manquants");
                        break;
                    }

                }

                System.out.println("Ajout");
                boolean bool = true;

                unDisplayImage();
                String repName = champName.getText();
                String repAuteur = champAuteur.getText();
                String repResume = champResume.getText();
                String repurlImage = urlImage.getText();

                int repColonne = 0, repRangee = 0, repParution = 0;
                if(champColonne.getText().matches("[0-9]*")
                        && champRangee.getText().matches("[0-9]*")
                        && champParution.getText().matches("[0-9]*")
                        && !champColonne.getText().equals("")
                        && !champParution.getText().equals("")
                        && !champRangee.getText().equals("") ) {

                    repColonne = Integer.parseInt(champColonne.getText());
                    repRangee = Integer.parseInt(champRangee.getText());
                    repParution = Integer.parseInt(champParution.getText());
                } else {
                    if (champColonne.getText().equals("") || champParution.getText().equals("") || champRangee.getText().equals("")){
                        txtError.setText("Veuillez renseignez les \nchamps manquants");
                        return;
                    }
                    if (!champColonne.getText().matches("[0-9]*") || champRangee.getText().matches("[0-9]*") || !champParution.getText().matches("[0-9]*") ){
                        txtError.setText("Veuillez rentrer des numéros positifs\npour les champs correspondants");
                        return;
                    }
                }

                // definition des différentes colonnes
                name.setCellValueFactory(new PropertyValueFactory<Book, String>("name"));
                auteur.setCellValueFactory(new PropertyValueFactory<Book, String>("auteur"));
                colonne.setCellValueFactory(new PropertyValueFactory<Book, String>("colonne"));
                rangee.setCellValueFactory(new PropertyValueFactory<Book, String>("rangee"));
                resume.setCellValueFactory(new PropertyValueFactory<Book, String>("resume"));
                parution.setCellValueFactory(new PropertyValueFactory<Book, String>("parution"));

                tabBib.setItems(books);

                Book myBook = new Book(repName, repAuteur, repResume, repColonne, repRangee, repParution,repurlImage);

                boolean conditionAdd = (!repName.equals("") && !repAuteur.equals("") && !Integer.toString(repColonne).equals("") && !Integer.toString(repRangee).equals("") && !Integer.toString(repParution).equals(""));
                boolean conditionColonne = (repColonne>=1 && repColonne <=7);
                boolean conditionRangee = (repRangee>=1 && repRangee <=7);
                boolean dateValide = (repParution <= anneeActuel && repParution >= 0);





                if (conditionAdd && conditionColonne && conditionRangee && dateValide) {

                    boolean boolVerifColonne = false;
                    try{
                        for (int i = 0; i < books.size(); i++) {
                            if ((books.get(i).getColonne() == repColonne) || boolVerifColonne){
                                boolVerifColonne = true;
                                if (books.get(i).getRangee() == repRangee){
                                    System.out.println("Cette position est déjà occupé");
                                    txtError.setText("Cette position est déjà occupé");
                                    break;
                                }
                            }
                    }
                    }catch (IndexOutOfBoundsException e){
                        System.out.println("index out of bounds");
                    }
                    if (!boolVerifColonne){
                        txtError.setText("");
                        books.add(myBook);
                        Clear();
                        contentMain.getChildren().remove(formumaire);
                    }
                }else if (!dateValide){
                    txtError.setText("Veuillez rentrez une date valide");
                }
                else if (!conditionColonne && !conditionRangee){
                    txtError.setText("Veuillez rentrez un numero \nde rangée et colonne valide");
                }
                else if (!conditionColonne){
                    txtError.setText("Veuillez saisir une colonne \ncomprise entre 1 et 7");
                }
                else if(!conditionRangee){
                    txtError.setText("Veuillez saisir une rangée \ncomprise entre 1 et 7");

                }else {
                    txtError.setText("Il manque des informations pour \nl'ajout du livre");
                }


            });
        });


        /* Voir les informations d'un livre */
        tabBib.setOnMouseClicked(selectChamp -> {
            unDisplayImage();


            ObservableList<Book> selectedItems = tabBib.getSelectionModel().getSelectedItems();


            if (selectedItems.size() != 0){
                TablePosition selectCell = tabBib.getSelectionModel().getSelectedCells().get(0);

                champName.setText(selectedItems.get(0).getName());
                champResume.setText(selectedItems.get(0).getResume());
                champParution.setText(Integer.toString(selectedItems.get(0).getParution()));
                champAuteur.setText(selectedItems.get(0).getAuteur());
                champColonne.setText(Integer.toString(selectedItems.get(0).getColonne()));
                champRangee.setText(Integer.toString(selectedItems.get(0).getRangee()));
                urlImage.setText(selectedItems.get(0).getUrl());
                if(!"".equals(urlImage.getText())) displayImage(urlImage.getText());


                if (!contentMain.getChildren().contains(formumaire)) {
                    contentMain.getChildren().add(formumaire);
                }

                btnValider.setOnMouseClicked(modifier1 -> {
                    String repName = champName.getText();
                    String repAuteur = champAuteur.getText();
                    String repResume = champResume.getText();
                    String repurlImage = urlImage.getText();
                    int repColonne = 0, repRangee = 0, repParution = 0;
                    if(champColonne.getText().matches("[0-9]*")
                            && champRangee.getText().matches("[0-9]*")
                            && champParution.getText().matches("[0-9]*")
                            && !champColonne.getText().equals("")
                            && !champParution.getText().equals("")
                            && !champRangee.getText().equals("") ) {

                        repColonne = Integer.parseInt(champColonne.getText());
                        repRangee = Integer.parseInt(champRangee.getText());
                        repParution = Integer.parseInt(champParution.getText());
                    } else {
                        if (champColonne.getText().equals("") || champParution.getText().equals("") || champRangee.getText().equals("")){
                            txtError.setText("Veuillez renseignez les \nchamps manquants");
                            return;
                        }
                        if (!champColonne.getText().matches("[0-9]*") || champRangee.getText().matches("[0-9]*") || !champParution.getText().matches("[0-9]*") ){
                            txtError.setText("Veuillez rentrer des numéros positifs\npour les champs correspondants");
                            return;
                        }
                    }

                    boolean conditionAdd = (!repName.equals("") && !repAuteur.equals("") && !Integer.toString(repColonne).equals("") && !Integer.toString(repRangee).equals("") && !Integer.toString(repParution).equals(""));
                    boolean conditionColonne = (repColonne>=1 && repColonne <=7);
                    boolean conditionRangee = (repRangee>=1 && repRangee <=7);
                    boolean dateValide = (repParution <= anneeActuel && repParution >= 0);


                    if (conditionAdd && conditionColonne && conditionRangee && dateValide) {
                        Book modifyBook = new Book(champName.getText(), champAuteur.getText(), champResume.getText(), Integer.parseInt(champColonne.getText()), Integer.parseInt(champRangee.getText()), Integer.parseInt(champParution.getText()), urlImage.getText());
                        books.set(selectCell.getRow(), modifyBook);
                        System.out.println("Modification");
                        Clear();
                        contentMain.getChildren().remove(formumaire);
                    }else if (!dateValide){
                        txtError.setText("Veuillez rentrez une date valide");
                    }
                    else if (!conditionColonne && !conditionRangee){
                        txtError.setText("Veuillez rentrez un numero \nde rangée et colonne valide");
                    }
                    else if (!conditionColonne){
                        txtError.setText("Veuillez saisir une colonne \ncomprise entre 1 et 7");
                    }else if (!conditionRangee){
                        System.out.println("Veuillez saisir une rangée comprise entre 1 et 7");
                    }
                    else {
                        txtError.setText("Il manque des informations pour \nla modification du livre");
                    }

                    suppr.setOnMouseClicked(supprLivre -> {


                        if (tabBib.getItems().size() == 0) {
                            txtError.setText("Aucun élément a supprimer");
                        } else {
                            if (tabBib.getItems().size() == 1) {  // supprime le formulaire des que le dernier book est supprimé
                                contentMain.getChildren().remove(formumaire);
                            }

                            TablePosition selectCellSupr = tabBib.getSelectionModel().getSelectedCells().get(0);
                            books.remove(selectCellSupr.getRow());
                            Clear();

                        }
                    });


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
        boolean backgroundLoading = true;
        String imgName = "" + imgUrl;
                txtNotFound.setText(" ");
                imgView.imageProperty().set(null);
                try {
                    myImage = new Image(imgUrl, backgroundLoading);
                    imgView.setImage(myImage);
                } catch(Exception ignore) {}
        }


    /* Effacement des champs après validation*/

        private void Clear(){
            champColonne.clear();
            champAuteur.clear();
            champParution.clear();
            champRangee.clear();
            champName.clear();
            champResume.clear();
            urlImage.clear();

        }


    }
