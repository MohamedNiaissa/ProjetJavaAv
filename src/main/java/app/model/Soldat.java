package app.model;

import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;

public class Soldat extends General {
    private final int indexID;
    private int soldatHP;
    private String soldatName, soldatGrade;
    private final TreeItem<String> soldat = new TreeItem<>("Soldier");
    private final Label soldatText = new Label();

    public Soldat(int ID, String Name, String Grade, int HP) {
        super();
        this.indexID = ID;
        this.soldatName = Name;
        this.soldatGrade = Grade;
        this.soldatHP = HP;
    }

    public void setTreeSoldat(TreeItem<String> parent) {
        parent.getChildren().add(soldat);
    }

    public void setSoldatGraphics(int index) {
        soldatText.setText("SOLDAT");
        soldat.setValue(soldat.getValue() + " " + index);
        this.soldat.setGraphic(soldatText);
    }

    public int getIndexID() {
        return this.indexID;
    }

    public int getSoldatHP() {
        return this.soldatHP;
    }

    public String getSoldatName() {
        return this.soldatName;
    }

    public String getSoldatGrade() {
        return this.soldatGrade;
    }

    public void setSoldatName(String newName) {
        this.soldatName = newName;
    }

    public void setSoldatGrade(String newGrade) {
        this.soldatGrade = newGrade;
    }

    public void setSoldatHP(int newHP) {
        this.soldatHP = newHP;
    }

    @Override
    public String toString() {
        return "" + indexID;
    }
}
