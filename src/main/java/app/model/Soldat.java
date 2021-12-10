package app.model;

import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;

public class Soldat extends General {
    /** This class is the last of its Tree, it doesn't store anything beside Variables used to
     *  edit a specific Soldier.
     */

    private int indexID;
    private int soldatHP;
    private String soldatName, soldatGrade;
    private final TreeItem<String> soldat = new TreeItem<>("");
    private final Label soldatText = new Label();

    public Soldat(int ID, String Name, String Grade, int HP) {
        super();
        this.indexID = ID;
        this.soldatName = Name;
        this.soldatText.setText(Name);
        this.soldatGrade = Grade;
        this.soldatHP = HP;
    }

    public Soldat() { }

    public void setTreeSoldat(TreeItem<String> parent) {
        parent.getChildren().add(soldat);
    }

    public void setSoldatGraphics() {
        soldatText.setText("SOLDAT");
        this.soldat.setGraphic(soldatText);
    }

    public TreeItem<String> getSoldat() {
        return this.soldat;
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

    public void setSoldatText(String Name) {
        this.soldatText.setText(Name);
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
