package controller;

import driver.BankService;
import driver.RetrofitClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Transaction;

import java.io.IOException;

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

    public void sendRequest() throws IOException {
        ObservableList<Transaction> transactions = FXCollections.observableArrayList();
        service.getTransactions().execute().body().forEach(transactionResponse -> transactions.add(transactionResponse.createTransaction()));
        controller.setData(transactions);
    }

}
