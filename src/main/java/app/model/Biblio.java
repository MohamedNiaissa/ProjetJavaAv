package app.model;

import java.util.ArrayList;
import java.util.List;

public class Biblio {
    private String ex, ex1, ex2;
    private final List<Book> bookList = new ArrayList<>();

    public Biblio() { };
    public Biblio(String ex, String ex1, String ex2) {
        this.ex = ex;
        this.ex1 = ex1;
        this.ex2 = ex2;
    }

    public String getEx() {
        return ex;
    }

    public String getEx1() {
        return ex1;
    }

    public String getEx2() {
        return ex2;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void add_book(Book thisBook) {
        this.bookList.add(thisBook);
    }
}

