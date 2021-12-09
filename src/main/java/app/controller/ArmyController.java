package app.controller;

import app.model.Army;
import app.model.General;
import app.model.Soldat;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ArmyController implements Initializable {
    private final List<String> treeViewSkinGenIndex = new ArrayList<>();
    private final List<String> treeViewSkinSolIndex = new ArrayList<>();
    private final ContextMenu menu = new ContextMenu();
    private final MenuItem add = new MenuItem();
    private final MenuItem edit = new MenuItem();
    private final Army army = new Army();

    @FXML private TreeView<String> TVArmy;
    @FXML private VBox TVStats;

    @FXML private Label lblName;
    @FXML private Label lblAtt;
    @FXML private Label lblHP;

    @FXML private TextField formName;
    @FXML private TextField formAtt;
    @FXML private TextField formHP;

    @FXML private Button btnAddEdit;

    private void createMenu(ContextMenu menu, MenuItem add, MenuItem edit) {
        add.setVisible(false);
        edit.setVisible(false);
        menu.getItems().addAll(add, edit);
        TVArmy.setContextMenu(menu);
    }

    private void editMenu(MenuItem add, String addName, MenuItem edit, String editName) {
        add.setText(addName);
        edit.setText(editName);
    }

    private void displayMenu(MenuItem add, boolean setAdd, MenuItem edit, boolean setEdit) {
        add.setVisible(setAdd);
        edit.setVisible(setEdit);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createMenu(menu, add, edit);

        army.setTreeRoot(TVArmy);
        army.setRootButton(TVArmy);
        TVArmy.setOnMouseClicked(this::mouseClicked);
    }

    private void hideAndResetStats(boolean setVisible, boolean setVisibleSP, String attStr, String btnStr) {
        TVStats.setVisible(setVisible);
        lblName.setVisible(setVisible);
        lblName.setText("Name");
        formName.setVisible(setVisible);
        formName.clear();
        lblAtt.setVisible(setVisible);
        lblAtt.setText(attStr);
        formAtt.setVisible(setVisibleSP);
        formAtt.clear();
        lblHP.setVisible(setVisibleSP);
        lblHP.setText("HP");
        formHP.setVisible(setVisibleSP);
        formHP.clear();
        btnAddEdit.setVisible(setVisible);
        btnAddEdit.setText(btnStr);
    }

    private void hideAndResetStats(boolean setVisibleSP, String nameStr, String attStr, int hpStr) {
        TVStats.setVisible(true);
        lblName.setVisible(true);
        lblName.setText(lblName.getText() + " : " + nameStr);
        lblAtt.setVisible(true);
        lblAtt.setText(lblAtt.getText() + " " +  attStr);
        lblHP.setVisible(setVisibleSP);
        lblHP.setText(lblHP.getText() + " : " +  hpStr);
    }

    private void managingCreateGeneral() {
        int GID = army.getArmyList().size();
        army.addGeneral(new General(army.getArmyList().size()));
        General general = army.getArmyList().get(GID);

        hideAndResetStats(false, false, "", "");
        hideAndResetStats(true,false,"Troop capacity : " + General.getSList().size(),"Add");

        btnAddEdit.setOnMouseClicked(mouseClick -> {
            general.setGeneralName(formName.getText());
            general.setTreeGeneral(TVArmy.getRoot());
            general.setGeneralButton(GID);

            hideAndResetStats(false, false, "", "");
            displayMenu(add, false, edit, false);
        });
    }

    private void managingEditGeneral(String TVSGenValue) {
        int GID = treeViewSkinGenIndex.indexOf(TVSGenValue);
        General general = army.getArmyList().get(GID);

        hideAndResetStats(false, false, "", "");
        hideAndResetStats(true,false,"Troop capacity : " + General.getSList().size(),"Edit");
        formName.setText(general.getGeneralName());

        btnAddEdit.setOnMouseClicked(mouseClick -> {
            general.setGeneralName(formName.getText());

            hideAndResetStats(false, false, "", "");
            displayMenu(add, false, edit, false);
        });
    }

    private void managingCreateSoldier(String TVSGenValue) {
        hideAndResetStats(false, false, "", "");
        hideAndResetStats(true, true, "Grade", "Add");

        btnAddEdit.setOnMouseClicked(mouseClick -> {
            int GID = treeViewSkinGenIndex.indexOf(TVSGenValue);
            General general = army.getArmyList().get(GID);
            general.addSoldat(new Soldat(General.getSList().size(), formName.getText(), formAtt.getText(),
                    Integer.parseInt(formHP.getText())));
            int SID = General.getSList().size() - 1;
            Soldat soldat = General.getSList().get(SID);
            soldat.setTreeSoldat(general.getTIG());
            soldat.setSoldatGraphics(SID);

            hideAndResetStats(false, false, "", "");
            displayMenu(add, false, edit, false);
        });
    }

    private void managingEditSoldier(String TVSSolValue) {
        int SID = treeViewSkinSolIndex.indexOf(TVSSolValue);
        Soldat soldat = General.getSList().get(SID);

        hideAndResetStats(false, false, "", "");
        hideAndResetStats(true, true, "Grade", "Edit");
        formName.setText(soldat.getSoldatName());
        formAtt.setText(soldat.getSoldatGrade());
        formHP.setText(String.valueOf(soldat.getSoldatHP()));

        btnAddEdit.setOnMouseClicked(mouseClick -> {
            soldat.setSoldatName(formName.getText());
            soldat.setSoldatGrade(formAtt.getText());
            soldat.setSoldatHP(Integer.parseInt(formHP.getText()));

            hideAndResetStats(false, false, "", "");
            displayMenu(add, false, edit, false);
        });
    }

    public void mouseClicked(MouseEvent getCell) {
        String TVS = getCell.getTarget().toString();
        if(!TVS.startsWith("TreeViewSkin$1") || TVS.contains("null")) return;
        String TVSGenValue = TVS.substring(TVS.length() - 10, TVS.length() - 1);
        System.out.println(TVSGenValue);

        if(TVS.contains("Army")) {
            displayMenu(add, true, edit, false);
            editMenu(add, "Add General", edit, "");

            if(getCell.getButton() == MouseButton.PRIMARY) {
                hideAndResetStats(false, false, "", "");
            } else add.setOnAction(mouseClick -> managingCreateGeneral());
        }

        if(TVS.contains("General")) {
            displayMenu(add, true, edit, true);
            editMenu(add, "Add Soldier", edit, "Edit General");
            if (!treeViewSkinGenIndex.contains(TVSGenValue)) treeViewSkinGenIndex.add(TVSGenValue);

            if(getCell.getButton() == MouseButton.PRIMARY) {
                displayGenStats(TVSGenValue);
            } else {
                add.setOnAction(mouseClick -> managingCreateSoldier(TVSGenValue));
                edit.setOnAction(mouseClick -> managingEditGeneral(TVSGenValue));
            }
        }

        if(TVS.contains("Soldier")) {
            displayMenu(add, false, edit, true);
            editMenu(add, "", edit, "Edit Soldier");
            if (!treeViewSkinSolIndex.contains(TVSGenValue)) treeViewSkinSolIndex.add(TVSGenValue);

            if(getCell.getButton() == MouseButton.PRIMARY) {
                displaySolStats(TVSGenValue);
            } else edit.setOnAction(mouseClick -> managingEditSoldier(TVSGenValue));
        }
    }

    private void displayGenStats(String TVSGenValue) {
        int GID = treeViewSkinGenIndex.indexOf(TVSGenValue);
        General general = army.getArmyList().get(GID);

        hideAndResetStats(false, false, "", "");
        hideAndResetStats(false, general.getGeneralName(), "Troop capacity : " + General.getSList().size(),0);
    }

    private void displaySolStats(String TVSSolValue) {
        int SID = treeViewSkinSolIndex.indexOf(TVSSolValue);
        Soldat soldat = General.getSList().get(SID);

        hideAndResetStats(false, false, "", "");
        hideAndResetStats(true, soldat.getSoldatName(), "Grade : " + soldat.getSoldatGrade(), soldat.getSoldatHP());
    }
}
