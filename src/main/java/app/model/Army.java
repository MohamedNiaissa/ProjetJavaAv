package app.model;

import javafx.scene.control.*;
import javafx.scene.control.skin.VirtualContainerBase;
import javafx.scene.layout.VBox;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Army {
    private final List<General> generalsList = new ArrayList<>();
    private final TreeItem<String> root = new TreeItem<>("Army");
    private final Label rootName = new Label();

    public Army() {}

    public void setTreeRoot(TreeView<String> treeView) {
        treeView.setRoot(root);
    }

    public void setRootButton(TreeView<String> treeView) {
        rootName.setText("ARMY");
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

    public TreeItem<String> getTreeRoot() {
        return root;
    }

    public void getSkinProperty() {
        try {
            Field flowField = VirtualContainerBase.class.getDeclaredField("flow");
            flowField.setAccessible(true);
            System.out.println(flowField.getInt(root));
        } catch (NoSuchFieldException ignored) {} catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "" + generalsList;
    }
}
