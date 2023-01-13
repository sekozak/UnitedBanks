package controller.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Tag;
import model.Transaction;

import java.util.*;

public class TransactionResponse {
    private final String id;
    private final String operationDate;
    private final String transactionType;
    private final String amount;
    private final String currency;
    private final String title;
    private final String balance;
    private final String bank;
    private final Set<Tag> tags;

    public TransactionResponse(String id, String operationDate, String transactionType, String amount, String currency, String title, String balance, String bank, Set<Tag> tags) {
        this.id = id;
        this.operationDate = operationDate;
        this.transactionType = transactionType;
        this.amount = amount;
        this.currency = currency;
        this.title = title;
        this.balance = balance;
        this.bank = bank;
        this.tags = tags;
    }


    public Transaction createTransaction() {
        return new Transaction(id, operationDate, transactionType, amount, currency, title, balance, bank, convertSetToList(tags));
    }

    private ObservableList<String> convertSetToList(Set<Tag> tags){
        ObservableList<String> tagList = FXCollections.observableArrayList();
        for(Tag tag : tags){
            tagList.add(tag.getName());
        }
        return tagList;
    }
}
