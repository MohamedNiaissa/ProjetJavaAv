package app.model;

import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;

import java.util.ArrayList;
import java.util.List;

public class General extends Army {
    private int GeneralID;
    private String GeneralName;
    private static final List<Soldat> soldatsList = new ArrayList<>();
    private final TreeItem<String> general = new TreeItem<>("General");
    private final Label generalText = new Label();

    public General() {}
    public General(int ID) {
        this.GeneralID = ID;
    }

    public void setGeneralName(String generalName) {
        this.GeneralName = generalName;
    }

    public void setTreeGeneral(TreeItem<String> root) {
        root.getChildren().add(general);
    }

    public void setGeneralButton(int index) {
        generalText.setText("GENERAL");
        general.setValue(general.getValue() + " " + index);
        this.general.setGraphic(generalText);
    }

    public TreeItem<String> getTIG() {
        return this.general;
    }

    public void addSoldat(Soldat soldat) {
        this.soldatsList.add(soldat);
    }

    public static List<Soldat> getSList() {
        return soldatsList;
    }

    public int getGIndexID() {
        return this.GeneralID;
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
//            soldat.setSoldatGraphics();
        }
    }

    public TreeItem<String> getGeneral() {
        return this.general;
    }

    public Label getGeneralText() {
        return this.generalText;
    }

    public String getGeneralName() {
        return this.GeneralName;
    }

    @Override
    public String toString() {
        return "" + GeneralID + soldatsList;
    }
}

