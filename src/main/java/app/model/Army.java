package app.model;

import javafx.scene.control.*;
import javafx.scene.control.skin.VirtualContainerBase;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Army {
    private final List<General> generalsList = new ArrayList<>();
    private final TreeItem<String> root = new TreeItem<>("");
    private final Label rootName = new Label();

    public Army() {}

    public void setTreeRoot(TreeView<String> treeView) {
        treeView.setRoot(root);
    }

    public void setRootButton(TreeView<String> treeView) {
        rootName.setText("Army");
        rootName.setMaxWidth(Double.MAX_VALUE);
        rootName.setStyle("-fx-background-color: red");
        treeView.getRoot().setGraphic(rootName);
    }

    public void addGeneral(General general) {
        this.generalsList.add(general);
    }

    public List<General> getArmyList() {
        return generalsList;
    }

    public Label getRootName() {
        return rootName;
    }

    public void setRootName(String Name) {
        this.rootName.setText(Name);
    }

    public TreeItem<String> getTreeRoot() {
        return root;
    }

    @Override
    public String toString() {
        return "" + generalsList;
    }
}
