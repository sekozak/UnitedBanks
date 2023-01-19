package presenter;

import controller.AppController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;
import model.Transaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class TransactionEditDialogPresenter {
    private AppController appController;
    private Transaction transaction;
    private Stage dialogStage;
    private boolean approved;
    private List<CheckBox> checkBoxes;
    private ObservableList<String> checkTags = FXCollections.observableArrayList();
    @FXML
    private Label IDTextField;
    @FXML
    private Label titleTextField;
    @FXML
    private VBox vBox1;
    @FXML
    private HBox tagChooserHBox;


    @FXML
    private void initialize(){
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(5);
        dropShadow.setOffsetY(5);
        dropShadow.setColor(Color.GRAY);

        vBox1.setEffect(dropShadow);
        tagChooserHBox.setSpacing(5);
        tagChooserHBox.setPadding(new Insets(5, 5, 5, 5));
        tagChooserHBox.setSpacing(5);
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setController(AppController appController) {
        this.appController = appController;
        setFilters();
        updateControls();
    }

    private void setFilters(){
        appController.tagList.forEach(tag -> vBox1.getChildren().add(new CheckBox(tag) ));

        Font font = Font.font("Verdana", FontPosture.REGULAR, 15);
        Iterator<Node> nodes = tagChooserHBox.getChildren().iterator();
        checkBoxes = new ArrayList<>();

        while (nodes.hasNext()) {
            VBox vBox = (VBox) nodes.next();
            vBox.setSpacing(5);

            Iterator<Node> vBoxNodes = vBox.getChildren().iterator();

            while (vBoxNodes.hasNext()) {
                Node node = vBoxNodes.next();
                if (node instanceof CheckBox) {
                    CheckBox checkBox = (CheckBox) node;
                    checkBoxes.add(checkBox);
                    checkBox.setFont(font);

                    checkBox.setOnAction((event) -> {
                        String tag = checkBox.getText();
                        if (checkTags.contains(tag)) checkTags.remove(tag);
                        else checkTags.add(tag);
                    });
                }
            }
        }
    }

    public void setData(Transaction transaction) {this.transaction = transaction;}

    public boolean isApproved() {
        return approved;
    }

    @FXML
    private void handleSaveAction(ActionEvent event) {
        appController.sentTransactionTagsUpdate(Integer.parseInt(transaction.getId()), checkTags);
    }
    @FXML
    private void handleCloseAction(ActionEvent event) {
        approved = true;
        dialogStage.close();
    }


    private void updateControls() {
        IDTextField.setText(transaction.getId());
        titleTextField.setText(transaction.getTitle());
        checkTags = transaction.getTags();
        for(CheckBox cb : checkBoxes){
            if(checkTags.contains(cb.getText())) cb.setSelected(true);
        }
    }
}
