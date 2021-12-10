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
import java.util.*;

public class ArmyController implements Initializable {
    private final boolean[] validator = {true, true, true};
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
    @FXML private Button btnValidity;

    MultipleSelectionModel test;

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
        test = TVArmy.getSelectionModel();
        TVArmy.setOnMouseClicked(this::mouseClicked);

        formName.setOnKeyTyped(keyTyped -> {
            if(!Objects.equals(formName.getText(), "")) {
                validator[0] = true;

                if(formName.getText().length() > 21) {
                    btnValidity.setText("Content Validity : Name Error -> Too many characters for the name, max characters is 20.");
                    formName.deletePreviousChar();
                    btnAddEdit.setDisable(true);
                    validator[0] = false;

                } else if(!formName.getText().matches("[a-zA-Z0-9]*")) {
                    btnValidity.setText("Content Validity : Name Error -> The name field only support alphabetical and number characters.");
                    btnAddEdit.setDisable(true);
                    validator[0] = false;
                }

                if(validator[0] && validator[1] && validator[2]) {
                    btnValidity.setText("Content Validity : No error detected.");
                    btnAddEdit.setDisable(false);
                }

            } else {
                btnValidity.setText("Content Validity : No error detected.");
                btnAddEdit.setDisable(false);
                validator[0] = true;
            }
        });

        formAtt.setOnKeyTyped(keyTyped -> {
            if(!Objects.equals(formAtt.getText(), "")) {
                validator[1] = true;

                if(formAtt.getText().length() > 16) {
                    btnValidity.setText("Content Validity : Attribute Error -> Too many characters for the attribute, max characters is 15.");
                    formAtt.deletePreviousChar();
                    btnAddEdit.setDisable(true);
                    validator[1] = false;

                } else if(!formAtt.getText().matches("[a-zA-Z]*")) {
                    btnValidity.setText("Content Validity : Attribute Error -> The attribute field only support alphabetical characters.");
                    btnAddEdit.setDisable(true);
                    validator[1] = false;
                }

                if(validator[0] && validator[1] && validator[2]) {
                    btnValidity.setText("Content Validity : No error detected.");
                    btnAddEdit.setDisable(false);
                }

            } else {
                btnValidity.setText("Content Validity : No error detected.");
                btnAddEdit.setDisable(false);
                validator[1] = true;
            }
        });

        formHP.setOnKeyTyped(keyTyped -> {
            if(!Objects.equals(formHP.getText(), "")) {
                validator[2] = true;

                if(formHP.getText().length() > 11) {
                    btnValidity.setText("Content Validity : HP Error -> Too many numbers attributed to the hp, max characters is 10.");
                    formName.deletePreviousChar();
                    btnAddEdit.setDisable(true);
                    validator[2] = false;

                } else if(!formHP.getText().matches("[0-9]*")) {
                    btnValidity.setText("Content Validity : HP Error -> The HP field only support numbers characters.");
                    btnAddEdit.setDisable(true);
                    validator[2] = false;
                }

                if(validator[0] && validator[1] && validator[2]) {
                    btnValidity.setText("Content Validity : No error detected.");
                    btnAddEdit.setDisable(false);
                }

            } else {
                btnValidity.setText("Content Validity : No error detected.");
                btnAddEdit.setDisable(false);
                validator[2] = true;
            }
        });
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
        btnValidity.setVisible(true);
        btnValidity.setText("Content Validity :");
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
        hideAndResetStats(false, false, "", "");
        hideAndResetStats(true,false,"","Add");

        btnAddEdit.setOnMouseClicked(mouseClick -> {
            int GID = army.getArmyList().size();
            army.addGeneral(new General(army.getArmyList().size(), formName.getText()));
            General general = army.getArmyList().get(GID);
            lblAtt.setText("Troop capacity : " + (general.getSList().size() - 1));
            general.setTreeGeneral(TVArmy.getRoot());
            general.setGeneralButton();

            hideAndResetStats(false, false, "", "");
            displayMenu(add, false, edit, false);
        });
    }

    private void managingEditGeneral() {
        General general = new General();
        for(General gen : army.getArmyList()) {
            if(test.getSelectedItems().contains(gen.getGeneral())) {
                general = gen;
            }
        }

        hideAndResetStats(false, false, "", "");
        hideAndResetStats(true,false,"Troop capacity : " + general.getSList().size(),"Edit");
        formName.setText(general.getGeneralName());

        General finalGeneral = general;
        btnAddEdit.setOnMouseClicked(mouseClick -> {
            finalGeneral.setGeneralName(formName.getText());
            finalGeneral.setGeneralText(formName.getText());

            hideAndResetStats(false, false, "", "");
            displayMenu(add, false, edit, false);
        });
    }

    private void managingCreateSoldier() {
        hideAndResetStats(false, false, "", "");
        hideAndResetStats(true, true, "Grade", "Add");

        btnAddEdit.setOnMouseClicked(mouseClick -> {
            General general = new General();
            for(General gen : army.getArmyList()) {
                if(test.getSelectedItems().contains(gen.getGeneral())) {
                    general = gen;
                }
            }


            int convertHp;
            try {
                convertHp = Integer.parseInt(formHP.getText());

            }catch (NumberFormatException ignore) {

                return;
            }
            general.addSoldat(new Soldat(general.getSList().size(), formName.getText(), formAtt.getText(),
                   convertHp));
            int SID = general.getSList().size() - 1;
            Soldat soldat = general.getSList().get(SID);
            soldat.setTreeSoldat(general.getTIG());
            soldat.setSoldatGraphics(SID);

            hideAndResetStats(false, false, "", "");
            displayMenu(add, false, edit, false);
        });
    }

    private void managingEditSoldier() {
        Soldat soldat = new Soldat();
        for(General gen : army.getArmyList()) {
            for(Soldat sol : gen.getSList()) {
                if(test.getSelectedItems().contains(sol.getSoldat())) {
                    soldat = sol;
                }
            }
        }

        hideAndResetStats(false, false, "", "");
        hideAndResetStats(true, true, "Grade", "Edit");
        formName.setText(soldat.getSoldatName());
        formAtt.setText(soldat.getSoldatGrade());
        formHP.setText(String.valueOf(soldat.getSoldatHP()));

        Soldat finalSoldat = soldat;
        btnAddEdit.setOnMouseClicked(mouseClick -> {
            finalSoldat.setSoldatName(formName.getText());
            finalSoldat.setSoldatGrade(formAtt.getText());

            int convertHpEdit;

            try{
                convertHpEdit = Integer.parseInt(formHP.getText());
            }catch (Exception ignored){
                return;
            }

            finalSoldat.setSoldatHP(convertHpEdit);
            finalSoldat.setSoldatText(formName.getText());

            hideAndResetStats(false, false, "", "");
            displayMenu(add, false, edit, false);
        });
    }

    public void mouseClicked(MouseEvent getCell) {
        General general = new General();
        for(General gen : army.getArmyList()) {
            if(test.getSelectedItems().contains(gen.getGeneral())) {
                general = gen;
            }
        }
        String word = "";
        if(test.getSelectedItems().contains(army.getTreeRoot())) word = "Army";
        for(General gen : army.getArmyList()) {
            if(test.getSelectedItems().contains(gen.getGeneral())) word = "General";
        }

        for(General gen1 : army.getArmyList()) {
            for(Soldat sol : gen1.getSList()) {
                if(test.getSelectedItems().contains(sol.getSoldat())) word = "Soldier";
            }
        }

        if(word.contains("Army")) {
            displayMenu(add, true, edit, false);
            editMenu(add, "Add General", edit, "");

            if(getCell.getButton() == MouseButton.PRIMARY) {
                hideAndResetStats(false, false, "", "");
            } else add.setOnAction(mouseClick -> managingCreateGeneral());
        }

        if(word.contains("General")) {
            displayMenu(add, true, edit, true);
            editMenu(add, "Add Soldier", edit, "Edit General");

            if(getCell.getButton() == MouseButton.PRIMARY) {
                displayGenStats();
            } else {
                add.setOnAction(mouseClick -> managingCreateSoldier());
                edit.setOnAction(mouseClick -> managingEditGeneral());
            }
        }

        if(word.contains("Soldier")) {
            displayMenu(add, false, edit, true);
            editMenu(add, "", edit, "Edit Soldier");

            if(getCell.getButton() == MouseButton.PRIMARY) {
                displaySolStats();
            } else edit.setOnAction(mouseClick -> managingEditSoldier());
        }
    }

    private void displayGenStats() {
        General general = new General();
        for(General gen : army.getArmyList()) {
            if(test.getSelectedItems().contains(gen.getGeneral())) {
                general = gen;
            }
        }

        hideAndResetStats(false, false, "", "");
        hideAndResetStats(false, general.getGeneralName(), "Troop capacity : " + general.getSList().size(),0);
    }

    private void displaySolStats() {
        Soldat soldat = new Soldat();
        for(General gen : army.getArmyList()) {
            for(Soldat sol : gen.getSList()) {
                if(test.getSelectedItems().contains(sol.getSoldat())) {
                    soldat = sol;
                }
            }
        }

        hideAndResetStats(false, false, "", "");
        hideAndResetStats(true, soldat.getSoldatName(), "Grade : " + soldat.getSoldatGrade(), soldat.getSoldatHP());
    }
}
