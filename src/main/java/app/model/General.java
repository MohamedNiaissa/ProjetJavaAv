package app.model;

import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class General extends Army {
    /** This class is the child of the Army, its role is more important than the two other classes.
     *  Its goal is to store Soldiers inside a list and be the bridge between Army and Soldier.
     *  The class mostly contains Setters and Getters.
     *  Although there is a sort method, it doesn't work.
     */

    private int GeneralID;
    private String GeneralName;
    private final List<Soldat> soldatsList = new ArrayList<>();
    private final TreeItem<String> general = new TreeItem<>("");
    private final Label generalText = new Label();

    public General() {}
    public General(int ID, String Name) {
        this.GeneralID = ID;
        this.GeneralName = Name;
        this.generalText.setText(Name);
    }

    public void setGeneralName(String generalName) {
        this.GeneralName = generalName;
    }

    public void setTreeGeneral(TreeItem<String> root) {
        root.getChildren().add(general);
    }

    public void setGeneralButton() {
        generalText.setText("GENERAL");
        this.general.setGraphic(generalText);
    }

    public TreeItem<String> getTIG() {
        return this.general;
    }

    public void addSoldat(Soldat soldat) {
        this.soldatsList.add(soldat);
    }

    public List<Soldat> getSList() {
        return this.soldatsList;
    }

    public void sortSoldat() {
        int maxHp;
        int indexSoldat = 0;
        List<Soldat> clone = new ArrayList<>(soldatsList);
        soldatsList.clear();

        while(clone.size() != 0) {
            maxHp = 0;
            for(Soldat soldat : clone) {
                if(maxHp <= soldat.getSoldatHP()) {
                    maxHp = soldat.getSoldatHP();
                    indexSoldat = clone.indexOf(soldat);
                }
            }
            soldatsList.add(clone.get(indexSoldat));
            clone.remove(clone.get(indexSoldat));
        }

        for(Soldat soldat : soldatsList) {
            soldat.setTreeSoldat(getTIG());
        }
    }

    public TreeItem<String> getGeneral() {
        return this.general;
    }

    public void setGeneralText(String Name) {
        this.generalText.setText(Name);
    }

    public String getGeneralName() {
        return this.GeneralName;
    }

    @Override
    public String toString() {
        return "" + GeneralID + soldatsList;
    }
}

