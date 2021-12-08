package app.controller;

import app.model.Biblio;
import app.model.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        contentMain.getChildren().remove(formumaire);

        ObservableList<Book> books = FXCollections.observableArrayList(

        );

        btnValider.setOnMouseClicked(addBook -> {

            displayImage("https://cdn.futura-sciences.com/buildsv6/images/wide1920/8/e/2/8e24d3c12f_98112_01-intro-1474.jpg");

            String repName = champName.getText();
            String repAuteur = champAuteur.getText();
            String repResume = champResume.getText();
            String repColonne = champColonne.getText();
            String repRangee = champRangee.getText();
            String repParution = champParution.getText();

            if ((repName != "" && repAuteur != "" && repResume != "" && repColonne != "" && repRangee != "" && repParution != "") || repName != "" && repAuteur != "" &&repColonne != "" && repRangee != "" && repParution != "" ){
                books.add(new Book(repName,repAuteur,repResume,repColonne,repRangee,repParution));
            }else {
                System.out.println("Veuillez remplir le champ manquant");
            }

            contentMain.getChildren().remove(formumaire);

        });

        plus.setOnMouseClicked(apparitionForm -> {
            contentMain.getChildren().add(formumaire);

        });



       /* urlImage.setOnKeyReleased(chargeImage -> {
        }); */

        // definition des différentes colonnes
        name.setCellValueFactory(new PropertyValueFactory<Book,String>("name"));
        auteur.setCellValueFactory(new PropertyValueFactory<Book,String>("auteur"));
        colonne.setCellValueFactory(new PropertyValueFactory<Book,String>("parution"));
        rangee.setCellValueFactory(new PropertyValueFactory<Book,String>("rangee"));
        resume.setCellValueFactory(new PropertyValueFactory<Book,String>("resume"));
        parution.setCellValueFactory(new PropertyValueFactory<Book,String>("parution"));

        tabBib.setItems(books);


        //biblio.add_book(new Book("Mon Aventure","Moi","lorem machin j'ai oublié le reste","2","1","2000"));


    }

    /**
     * displays linked image of a book
     * @param imgUrl image link stored with the book details in biblio/library
     *               image can be local if name is not beginning with "http"
     */
    private void displayImage(String imgUrl) {
        boolean imgIsLocal = true;
        boolean imgFound = true;

        Image myImage = null;
        boolean backgroundLoading = true;
        String imgName = urlImage.getText();
        if (imgName.substring(0,4).equals("http")) imgIsLocal = false;
        else imgUrl = "/Users/cyrille/IdeaProjects/ProjetJavaAv/src/main/resources/app/img/interdit.png";

        if(imgIsLocal) {
            InputStream stream = null;
            try {
                System.out.println(imgUrl);
                stream = new FileInputStream(imgUrl);
            } catch (FileNotFoundException e) {
                imgFound = false;
                System.out.println("Stream impossible ...");
                e.printStackTrace();
            }
            if(imgFound) myImage = new Image(stream);
        } else myImage = new Image(imgUrl, backgroundLoading);

        if(imgFound) imgView.setImage(myImage);
    }
}
