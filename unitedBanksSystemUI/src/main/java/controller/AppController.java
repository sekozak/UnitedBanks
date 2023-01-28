package controller;

import driver.BankService;
import driver.RetrofitClient;
import driver.model.Pages;
import driver.model.PagesResponse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Transaction;
import presenter.TransactionEditDialogPresenter;

import java.io.IOException;
import java.util.List;

public class AppController {

    private final Stage primaryStage;
    private TransactionController controller;
    private final BankService service;
    public List<String> tagList;


    public AppController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        service = new RetrofitClient().getRetrofitClient().create(BankService.class);
    }

    public void initRootLayout() {
        try {
            this.primaryStage.setTitle("BankosDeLaKolunios");
            getTagList();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AppController.class.getResource("../view/TransactionsPane.fxml"));
            BorderPane rootLayout = loader.load();

            controller = loader.getController();
            controller.setAppController(this);
            controller.setTagList(tagList);

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean showTransactionEditDialog(Transaction transaction) {
        try {
            // Load the fxml file and create a new stage for the dialog
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(TransactionController.class.getResource("../view/TransactionEditDialog.fxml"));
            BorderPane pane = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Tag manager");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(pane);
            dialogStage.setScene(scene);

            // Set the transaction into the presenter.
            TransactionEditDialogPresenter presenter = loader.getController();
            presenter.setDialogStage(dialogStage);
            presenter.setData(transaction);
            presenter.setTagList(tagList);
            presenter.setController(this);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            return presenter.isApproved();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }



    public void sentTransactionTagsUpdate(Integer transactionId, List<String> tags) {
        try {
            service.updateTransactionTags(transactionId, tags).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void getTagList() {
        try {
            this.tagList = service.getTagList().execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendRequestForPages(Integer page, Integer size, List<String> tags) {
        ObservableList<Transaction> transactions = FXCollections.observableArrayList();
        PagesResponse pagesResponse;
        Pages pages;
        Integer lastPage=0;
        try {
            pagesResponse = service.getPages(page, size, tags).execute().body();
            assert pagesResponse != null;
            pages = pagesResponse.createPage();
            transactions.addAll(pages.getTransactions());
            lastPage=pages.getTotalPages();
        } catch (IOException e) {
            e.printStackTrace();
        }


        controller.setData(transactions);
        controller.setLastPageNumber(lastPage);
    }

}
