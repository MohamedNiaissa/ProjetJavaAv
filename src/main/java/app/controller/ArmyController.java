package app.controller;

import app.model.Army;
import app.model.General;
import app.model.Soldat;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.net.URL;
import java.util.ResourceBundle;

public class ArmyController implements Initializable {
    Army army = new Army();

    @FXML private TreeView<String> TVArmy;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        army.setTreeRoot(TVArmy);
        army.setRootButton(TVArmy);

        army.getRootButton().setOnMouseClicked(e -> {
            army.addGeneral(new General(army.getArmyList().size()));
            int GID = army.getArmyList().size() - 1;
            General general = army.getArmyList().get(GID);
            System.out.println(army);
            general.setTreeGeneral(TVArmy.getRoot());
            general.setGeneralButton();

            general.getGeneralButton().setOnMouseClicked(ev -> {
                general.addSoldat(new Soldat(general.getSList().size(), "Bob", 0, 67 + general.getSList().size()));
                int ID = general.getSList().size() - 1;
                Soldat soldat = general.getSList().get(ID);
                soldat.setTreeSoldat(general.getTIG());
                soldat.setSoldatGraphics();
            });

            general.getGeneralText().setOnMouseClicked(evv -> {
                general.getGeneral().getChildren().clear();
                general.sortSoldat();
            });
        });
    }
}
