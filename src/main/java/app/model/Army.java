package app.model;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class Army {
    private final List<General> generalsList = new ArrayList<>();
    private final TreeItem<String> root = new TreeItem<>(" ");
    private final Button btnT = new Button("Create General");
    private final Label rootName = new Label();

    public Army() {}

    public void setTreeRoot(TreeView<String> treeView) {
        treeView.setRoot(root);
    }

    public void setRootButton(TreeView<String> treeView) {
        VBox cellVBox = new VBox(10);
        rootName.setText("ARMY");
        cellVBox.getChildren().addAll(rootName,btnT);
        treeView.getRoot().setGraphic(cellVBox);
    }

    public Button getRootButton() {
        return this.btnT;
    }

    public void addGeneral(General general) {
        this.generalsList.add(general);
    }

    public List<General> getArmyList() {
        return generalsList;
    }

    @Override
    public String toString() {
        return "" + generalsList;
    }
}
