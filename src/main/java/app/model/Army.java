package app.model;

import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class Army {
    /** The class army is the parent of General and the grand-parent of the Soldier.
     *  It will store in a List<General> every General Object created from the ArmyController.
     *  The class mostly contains getter and setter.
     */

    private final List<General> generalsList = new ArrayList<>();
    private final TreeItem<String> root = new TreeItem<>("");
    private final Label rootName = new Label();

    public Army() {}

    public void setTreeRoot(TreeView<String> treeView) {
        treeView.setRoot(root);
    }

    public void setRootAttribute(TreeView<String> treeView) {
        rootName.setText("Army");
        treeView.getRoot().setGraphic(rootName);
    }

    public void addGeneral(General general) {
        this.generalsList.add(general);
    }

    public List<General> getArmyList() {
        return generalsList;
    }

    public TreeItem<String> getTreeRoot() {
        return root;
    }

    @Override
    public String toString() {
        return "" + generalsList;
    }
}
