package controller;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import model.Transaction;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class TransactionController {
    private ObservableList<Transaction> transactions;
    private AppController appController;
    private final List<String> checkTags = new ArrayList<>();
    @FXML
    private TableView<Transaction> transactionsTable;
    @FXML
    private TableColumn<Transaction, String> idColumn;
    @FXML
    private TableColumn<Transaction, String> operationDateColumn;
    @FXML
    private TableColumn<Transaction, String> transactionTypeColumn;
    @FXML
    private TableColumn<Transaction, String> amountColumn;
    @FXML
    private TableColumn<Transaction, String> currencyColumn;
    @FXML
    private TableColumn<Transaction, String> titleColumn;
    @FXML
    private TableColumn<Transaction, String> balanceColumn;
    @FXML
    private TableColumn<Transaction, String> bankColumn;
    @FXML
    private Button requestButton;
    @FXML
    private VBox vBox1;
    @FXML
    private VBox mainVBox;
    @FXML
    private HBox tagChooserHBox;
    @FXML
    private Label title;
    @FXML
    private Button editButton;


    @FXML
    private void initialize() {
        transactionsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        idColumn.setCellValueFactory(dataValue -> dataValue.getValue().idProperty());
        operationDateColumn.setCellValueFactory(dataValue -> dataValue.getValue().operationDateProperty());
        transactionTypeColumn.setCellValueFactory(dataValue -> dataValue.getValue().transactionTypeProperty());
        amountColumn.setCellValueFactory(dataValue -> dataValue.getValue().amountProperty());
        currencyColumn.setCellValueFactory(dataValue -> dataValue.getValue().currencyProperty());
        titleColumn.setCellValueFactory(dataValue -> dataValue.getValue().titleProperty());
        balanceColumn.setCellValueFactory(dataValue -> dataValue.getValue().balanceProperty());
        bankColumn.setCellValueFactory(dataValue -> dataValue.getValue().bankProperty());

        transactionsTable
                .getSelectionModel()
                .setSelectionMode(SelectionMode.MULTIPLE);

        editButton
                .disableProperty()
                .bind(Bindings
                        .size(transactionsTable
                                .getSelectionModel()
                                .getSelectedItems())
                        .isNotEqualTo(1));


        title.setFont(Font.font("Verdana", FontPosture.REGULAR, 15));
        title.setPadding(new Insets(5, 5, 5, 5));
        title.setTextFill(Color.BLUE);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(5);
        dropShadow.setOffsetY(5);
        dropShadow.setColor(Color.GRAY);

        vBox1.setEffect(dropShadow);
        mainVBox.setStyle("-fx-background-color:lightgrey");
        tagChooserHBox.setSpacing(5);
        tagChooserHBox.setPadding(new Insets(5, 5, 5, 5));
        tagChooserHBox.setSpacing(5);

        Font font = Font.font("Verdana", FontPosture.REGULAR, 15);
        Iterator<Node> nodes = tagChooserHBox.getChildren().iterator();
        List<CheckBox> checkBoxes = new ArrayList<>();

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

                        try {
                            if(checkTags.size()!=0) sendRequestForTaggedTransactions();
                            else sendRequestForAllTransactions();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });

                }
            }

        }

    }

    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    public void setData(ObservableList<Transaction> transactions) {
        this.transactions = transactions;
        transactionsTable.setItems(this.transactions);
    }

    @FXML
    private void sendRequestForAllTransactions() throws IOException {
        appController.sendRequestForAllTransactions();
    }

    private void sendRequestForTaggedTransactions() throws IOException {
        appController.sendRequestForTaggedTransactions(checkTags);
    }

    @FXML
    private void handleEditAction(ActionEvent event) {
        Transaction transaction = transactionsTable.getSelectionModel().getSelectedItem();
        if (transaction != null) {
            appController.showTransactionEditDialog(transaction);
        }
    }


}
