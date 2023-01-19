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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class TransactionController {
    private ObservableList<Transaction> transactions;
    private AppController appController;
    private final List<String> checkTags = new ArrayList<>();
    private final List<String> tagList = new ArrayList<>();
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
    private VBox vBox1;
    @FXML
    private VBox mainVBox;
    @FXML
    private HBox tagChooserHBox;
    @FXML
    public HBox pagingBox;
    @FXML
    private Label title;
    @FXML
    private Button editButton;
    @FXML
    private Button firstButton, previousButton, nextButton, lastButton;
    @FXML
    private Label pageNumber;
    private Integer size = 20;
    private Integer currentPage = 1, lastPage = 1;


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

        HBox.setMargin(pageNumber, new Insets(5));

        firstButton.setOnAction(event -> {
            currentPage=1;
            getPage(0);
        });
        nextButton.setOnAction(event -> {
            if(currentPage<lastPage) {
                getPage(++currentPage-1);
            }
        });
        previousButton.setOnAction(event -> {
            if(currentPage>1) {
                getPage(--currentPage-1);
            }
        });
        lastButton.setOnAction(event -> {
            currentPage=lastPage;
            getPage(currentPage-1);
        });
    }

    public void setAppController(AppController appController) {
        this.appController = appController;
        setFilters();
        getPage(0);
    }


    private void setFilters(){
        Font font = Font.font("Verdana", FontPosture.REGULAR, 15);
        List<CheckBox> checkBoxes = new ArrayList<>();

        appController.tagList.forEach(tag -> vBox1.getChildren().add(new CheckBox(tag) ));
        Iterator<Node> vBoxNodes = vBox1.getChildren().iterator();

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

                    currentPage=1;
                    getPage(0);
                });
            }
        }
    }

    public void setData(ObservableList<Transaction> transactions) {
        this.transactions = transactions;
        transactionsTable.setItems(this.transactions);
    }

    public void setLastPageNumber(Integer last) {
        this.lastPage=last;
    }

    @FXML
    private void handleEditAction(ActionEvent event) {
        Transaction transaction = transactionsTable.getSelectionModel().getSelectedItem();
        if (transaction != null) {
            appController.showTransactionEditDialog(transaction);
        }
    }

    private void getPage(Integer page){
        if(checkTags.size()==0) appController.sendRequestForPages(page, size, null);
        else appController.sendRequestForPages(page, size, checkTags);
        pageNumber.setText(currentPage.toString());
    }

}
