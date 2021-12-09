package app.model;

import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;

public class Soldat extends General {
    private final int indexID;
    private final int soldatGrade, soldatHP;
    private final String soldatName;
    private final TreeItem<String> soldat = new TreeItem<>(" ");
    private final Label soldatText = new Label();

    public Soldat(int ID, String Name, int Grade, int HP) {
        super();
        this.indexID = ID;
        this.soldatName = Name;
        this.soldatGrade = Grade;
        this.soldatHP = HP;
    }

    public void setTreeSoldat(TreeItem<String> parent) {
        parent.getChildren().add(soldat);
    }

    public void setSoldatGraphics() {
        soldatText.setText("SOLDAT\n" + "Name : " + soldatName + " , Grade : " + soldatGrade + " , HP : " + soldatHP);
        this.soldat.setGraphic(soldatText);
    }

    public int getIndexID() {
        return this.indexID;
    }

    public int getSoldatHP() {
        return this.soldatHP;
    }

    @Override
    public String toString() {
        return "" + indexID;
    }
}
