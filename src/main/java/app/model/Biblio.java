package app.model;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Biblio {
    /*Declaration des différents éléments du constructeur qui serviront au remplissage de la bibliothèeque*/
    private String name,auteur,resume,url;
    private int colonne,rangee,parution;

    public Biblio() { };

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Biblio(String name, String auteur, String resume, int colonne, int rangee, int parution, String url) {
        this.name = name;
        this.auteur = auteur;
        this.resume = resume;
        this.colonne = colonne;
        this.rangee = rangee;
        this.parution = parution;
        this.url = url;

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

    public int getColonne() {
        return colonne;
    }

    public void setColonne(int colonne) {
        this.colonne = colonne;
    }

    public int getRangee() {
        return rangee;
    }

    public void setRangee(int rangee) {
        this.rangee = rangee;
    }

    public int getParution() {
        return parution;
    }

    public void setParution(int parution) {
        this.parution = parution;
    }



}

