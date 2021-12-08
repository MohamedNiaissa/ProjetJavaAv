package app.controller;

import app.model.Biblio;
import app.model.Book;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class BibController implements Initializable {
    Biblio biblio = new Biblio();
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String a = " ";
        String b = " a";
        String c = " b";

        biblio.add_book(new Book(a,b,c));
    }
}
