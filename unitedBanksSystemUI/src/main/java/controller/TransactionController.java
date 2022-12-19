package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Transaction;

import java.io.IOException;


public class TransactionController {

    private ObservableList<Transaction> transactions;

    private AppController appController;

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

    }

    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    public void setData(ObservableList<Transaction> transactions) {
        this.transactions = transactions;
        transactionsTable.setItems(this.transactions);
    }

    @FXML
    private void sendRequest(ActionEvent event) throws IOException {
        appController.sendRequest();
    }

}
