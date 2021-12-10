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
    // Validator is used for the textField content validation.
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

    // This class will be used as a cell selector.
    MultipleSelectionModel selected;

    private void createMenu(ContextMenu menu, MenuItem add, MenuItem edit) {
        /* We create a simple menu when using the right click, we set the menuItem visibility to false
         *  until a special event trigger it back to visible.
         */
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
        /* We initialize the TreeRoot of the TreeView and its attribute.
         *  We set an event on the whole TreeView rather than a single cell.
         *  This method will also set a series of verification when entering an input in x TextField.
         *  To do so we will use boolean validator declared before.
         *  If there is a single false in the array, the button to send the information will stay disabled.
         */

        createMenu(menu, add, edit);
        army.setTreeRoot(TVArmy);
        army.setRootAttribute(TVArmy);
        selected = TVArmy.getSelectionModel();
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
        /* This function will simply reset the elements needed each time the form is called.
         */
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
        /* This function does the same thing as the one above, but the only difference is that
         *  this one is used for the edit option.
         */
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
            /* The GID is the General ID.
             *  We then proceed to create an Object General and store it in the class Army Array.
             */
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
        /* First, we start by getting the General from the selected cell.
         *  Then we edit this Object with some setters.
         */

        General general = new General();
        for(General gen : army.getArmyList()) {
            if(selected.getSelectedItems().contains(gen.getGeneral())) {
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
                if(selected.getSelectedItems().contains(gen.getGeneral())) {
                    general = gen;
                }
            }

            general.addSoldat(new Soldat(general.getSList().size(), formName.getText(), formAtt.getText(),
                    Integer.parseInt(formHP.getText())));
            int SID = general.getSList().size() - 1;
            Soldat soldat = general.getSList().get(SID);
            soldat.setTreeSoldat(general.getTIG());
            soldat.setSoldatGraphics();

            hideAndResetStats(false, false, "", "");
            displayMenu(add, false, edit, false);
        });
    }

    private void managingEditSoldier() {
        /* First, we start by going through every general, and for each we search the selected
         *  Solider. Then we edit this Object with some setters.
         */

        Soldat soldat = new Soldat();
        for(General gen : army.getArmyList()) {
            for(Soldat sol : gen.getSList()) {
                if(selected.getSelectedItems().contains(sol.getSoldat())) {
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
            finalSoldat.setSoldatHP(Integer.parseInt(formHP.getText()));
            finalSoldat.setSoldatText(formName.getText());

            hideAndResetStats(false, false, "", "");
            displayMenu(add, false, edit, false);
        });
    }

    public void mouseClicked(MouseEvent getCell) {
        /* The main event listener, this function will determine which cell it is without the help
         *  from naming a TreeItem. It check if the selected element exist, then define
         *  the string with a specific name.
         *  We cannot use the getCell event to fetch the cell as it send back a TreeViewSkin since
         *  we can't add an event listener or point the Object index as it change every so often.
         */

        String word = "";
        if(selected.getSelectedItems().contains(army.getTreeRoot())) word = "Army";
        for(General gen : army.getArmyList()) {
            if(selected.getSelectedItems().contains(gen.getGeneral())) word = "General";
        }

        for(General gen1 : army.getArmyList()) {
            for(Soldat sol : gen1.getSList()) {
                if(selected.getSelectedItems().contains(sol.getSoldat())) word = "Soldier";
            }
        }

        // If the cell is Army, then do the Army related option.
        if(word.contains("Army")) {
            displayMenu(add, true, edit, false);
            editMenu(add, "Add General", edit, "");

            if(getCell.getButton() == MouseButton.PRIMARY) {
                hideAndResetStats(false, false, "", "");
            } else add.setOnAction(mouseClick -> managingCreateGeneral());
        }

        // If the cell is General, then do the General related option.
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

        // If the cell is Solider, then do the Soldier related option
        if(word.contains("Soldier")) {
            displayMenu(add, false, edit, true);
            editMenu(add, "", edit, "Edit Soldier");

            if(getCell.getButton() == MouseButton.PRIMARY) {
                displaySolStats();
            } else edit.setOnAction(mouseClick -> managingEditSoldier());
        }
    }

    private void displayGenStats() {
        /* This function display the General stats when selecting the cell, but it's on ReadOnly.
         */

        General general = new General();
        for(General gen : army.getArmyList()) {
            if(selected.getSelectedItems().contains(gen.getGeneral())) {
                general = gen;
            }
        }

        hideAndResetStats(false, false, "", "");
        hideAndResetStats(false, general.getGeneralName(), "Troop capacity : " + general.getSList().size(),0);
    }

    private void displaySolStats() {
        /* This function display the Soldier stats when selecting the cell, but it's on ReadOnly.
         */

        Soldat soldat = new Soldat();
        for(General gen : army.getArmyList()) {
            for(Soldat sol : gen.getSList()) {
                if(selected.getSelectedItems().contains(sol.getSoldat())) {
                    soldat = sol;
                }
            }
        }

        hideAndResetStats(false, false, "", "");
        hideAndResetStats(true, soldat.getSoldatName(), "Grade : " + soldat.getSoldatGrade(), soldat.getSoldatHP());
    }
}
