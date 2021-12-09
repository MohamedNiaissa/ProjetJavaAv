package app.model;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class General extends Army {
    private int GeneralID;
    private String GeneralName;
    private final List<Soldat> soldatsList = new ArrayList<>();
    private final TreeItem<String> general = new TreeItem<>(" ");
    private final Button generalCB = new Button("Create Soldat");
    private final Button generalEdit = new Button("Edit general");
    private final Label generalText = new Label();

    public General() {}
    public General(int ID) {
        this.GeneralID = ID;
    }

    public void setTreeGeneral(TreeItem<String> root) {
        root.getChildren().add(general);
    }

    public void setGeneralButton() {
        VBox cellVbox = new VBox(10);
        HBox cellHBox = new HBox(10);
        generalText.setText("GENERAL\n" + "Name : " + GeneralName +  " , Troops size : " + soldatsList.size());
        cellHBox.getChildren().addAll(generalCB, generalEdit);
        cellVbox.getChildren().addAll(generalText, cellHBox);
//        this.general.setGraphic(generalCB);
        this.general.setGraphic(cellVbox);
//        this.general.setValue(generalCB + "hello");
    }

    public TreeItem<String> getTIG() {
        return this.general;
    }

    public Button getGeneralButton() {
        return this.generalCB;
    }

    public void addSoldat(Soldat soldat) {
        this.soldatsList.add(soldat);
    }

    public List<Soldat> getSList() {
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
            soldat.setSoldatGraphics();
        }
    }

    public TreeItem<String> getGeneral() {
        return this.general;
    }

    public Label getGeneralText() {
        return this.generalText;
    }

    @Override
    public String toString() {
        return "" + GeneralID + soldatsList;
    }
}

