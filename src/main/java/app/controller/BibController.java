package app.controller;

import app.model.Biblio;
import app.model.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        contentMain.getChildren().remove(formumaire);


        /* List vue par JavaFX*/
        ObservableList<Book> books = FXCollections.observableArrayList();



        plus.setOnMouseClicked(apparitionForm -> {

            contentMain.getChildren().add(formumaire);

            btnValider.setOnMouseClicked(addBook -> {

                System.out.println("Ajout");

                String repName = champName.getText();
                String repAuteur = champAuteur.getText();
                String repResume = champResume.getText();
                int repColonne = Integer.parseInt(champColonne.getText());
                int repRangee = Integer.parseInt(champRangee.getText());
                int repParution = Integer.parseInt(champParution.getText());


                // definition des différentes colonnes
                name.setCellValueFactory(new PropertyValueFactory<Book,String>("name"));
                auteur.setCellValueFactory(new PropertyValueFactory<Book,String>("auteur"));
                colonne.setCellValueFactory(new PropertyValueFactory<Book,String>("colonne"));
                rangee.setCellValueFactory(new PropertyValueFactory<Book,String>("rangee"));
                resume.setCellValueFactory(new PropertyValueFactory<Book,String>("resume"));
                parution.setCellValueFactory(new PropertyValueFactory<Book,String>("parution"));

                tabBib.setItems(books);

                Book myBook = new Book(repName,repAuteur,repResume,repColonne,repRangee,repParution);

                if (!repName.equals("") && !repAuteur.equals("") && !Integer.toString(repColonne).equals("") && !Integer.toString(repRangee).equals("") && !Integer.toString(repParution).equals("")){
                    books.add(myBook);
                    /* Effacement des champs après validation*/
                    champColonne.clear();
                    champAuteur.clear();
                    champParution.clear();
                    champRangee.clear();
                    champName.clear();
                    champResume.clear();
                    contentMain.getChildren().remove(formumaire);
                }else{
                    System.out.println("Veuillez remplir le champ manquant");
                }
            });
        });


        /* Voir les informations d'un livre */
        tabBib.setOnMouseClicked(selectChamp-> {

            ObservableList<Book> selectedItems = tabBib.getSelectionModel().getSelectedItems();
            System.out.println(selectedItems.get(0));

            TablePosition selectCell = tabBib.getSelectionModel().getSelectedCells().get(0);


            champName.setText(selectedItems.get(0).getName());
            champResume.setText(selectedItems.get(0).getResume());
            champParution.setText(Integer.toString(selectedItems.get(0).getParution()));
            champAuteur.setText(selectedItems.get(0).getAuteur());
            champColonne.setText(Integer.toString(selectedItems.get(0).getColonne()));
            champRangee.setText(Integer.toString(selectedItems.get(0).getRangee()));

            contentMain.getChildren().add(formumaire);

            btnValider.setOnMouseClicked(modifier1 -> {
                String repName = champName.getText();
                String repAuteur = champAuteur.getText();
                String repResume = champResume.getText();
                int repColonne = Integer.parseInt(champColonne.getText());
                int repRangee = Integer.parseInt(champRangee.getText());
                int repParution = Integer.parseInt(champParution.getText());

                if ((!repName.equals("") && !repAuteur.equals("") && !Integer.toString(repColonne).equals("") && !Integer.toString(repRangee).equals("") && !Integer.toString(repParution).equals(""))) {
                    Book modifyBook = new Book(champName.getText(),champAuteur.getText(),champResume.getText(),Integer.parseInt(champColonne.getText()),Integer.parseInt(champRangee.getText()),Integer.parseInt(champParution.getText()));
                    books.set(selectCell.getRow(),modifyBook);
                    System.out.println("Modification");
                    champColonne.clear();
                    champAuteur.clear();
                    champParution.clear();
                    champRangee.clear();
                    champName.clear();
                    champResume.clear();

                    contentMain.getChildren().remove(formumaire);
                }else{
                    System.out.println("Il manque des informations pour la modifications ...");
                }

            });


            suppr.setOnMouseClicked(supprLivre -> {


                if (tabBib.getItems().size() == 0){
                    System.out.println("Rien a supprimer");
                }else {

                    if (tabBib.getItems().size() == 1){  // supprime le formulaire des que le dernier book est supprimé
                        contentMain.getChildren().remove(formumaire);
                    }

                    TablePosition selectCellSupr = tabBib.getSelectionModel().getSelectedCells().get(0);
                    books.remove(selectCellSupr.getRow());

                    champColonne.clear();
                    champAuteur.clear();
                    champParution.clear();
                    champRangee.clear();
                    champName.clear();
                    champResume.clear();
                }

            });



        });

    }
}
