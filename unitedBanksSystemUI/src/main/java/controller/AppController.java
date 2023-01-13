package controller;

import driver.BankService;
import driver.RetrofitClient;
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


    public AppController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        service = new RetrofitClient().getRetrofitClient().create(BankService.class);
    }

    public void initRootLayout() {
        try {
            this.primaryStage.setTitle("BankosDeLaKolunios");

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AppController.class.getResource("../view/TransactionsPane.fxml"));
            BorderPane rootLayout = loader.load();

            controller = loader.getController();
            controller.setAppController(this);

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
            BorderPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Tag manager");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the transaction into the presenter.
            TransactionEditDialogPresenter presenter = loader.getController();
            presenter.setDialogStage(dialogStage);
            presenter.setData(transaction);
            presenter.setController(this);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            return presenter.isApproved();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void sendRequestForAllTransactions() throws IOException {
        ObservableList<Transaction> transactions = FXCollections.observableArrayList();
        service.getTransactions().execute().body().forEach(transactionResponse -> transactions.add(transactionResponse.createTransaction()));
        controller.setData(transactions);
    }

    public void sendRequestForTaggedTransactions(List<String> checkTags) throws IOException {
        ObservableList<Transaction> transactions = FXCollections.observableArrayList();
        service.getTaggedTransactions(checkTags).execute().body().forEach(transactionResponse -> transactions.add(transactionResponse.createTransaction()));
        controller.setData(transactions);
    }

    public void sentTransactionTagsUpdate(Integer transactionId, List<String> tags) {
        try {
            service.updateTransactionTags(transactionId, tags).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    };
}
