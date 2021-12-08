package app.model;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Biblio {
    private String name,auteur,resume,colonne,rangee,parution;

    private final List<Book> bookList = new ArrayList<>();

    public Biblio() { };
    public Biblio(String name, String auteur, String resume,String colonne, String rangee, String parution) {
        this.name = name;
        this.auteur = auteur;
        this.resume = resume;
        this.colonne = colonne;
        this.rangee = rangee;
        this.parution = parution;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getColonne() {
        return colonne;
    }

    public void setColonne(String colonne) {
        this.colonne = colonne;
    }

    public String getRangee() {
        return rangee;
    }

    public void setRangee(String rangee) {
        this.rangee = rangee;
    }

    public String getParution() {
        return parution;
    }

    public void setParution(String parution) {
        this.parution = parution;
    }

    public List<Book> getBookList() {
        return bookList;
    }



    public void add_book(Book thisBook) {
        this.bookList.add(thisBook);
    }




}

