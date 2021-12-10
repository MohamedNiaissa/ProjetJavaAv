package app.model;

public class Book extends Biblio {
    /**
     * Instancie un livre en recuperant les informations de la classe Biblio
     * @param name Nom du livre
     * @param auteur Nom de l'auteur
     * @param resume Résumé du livre
     * @param colonne rangé dans quelle colonne
     * @param rangee rangé dans quelle rangée
     * @param parution date de parution
     * @param url Lien vers une image sur le web
     */

    public Book(String name, String auteur, String resume,int colonne,int rangee, int parution,String url) {
        super(name,auteur,resume,colonne,rangee,parution,url);
    }
}
