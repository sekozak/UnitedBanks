package model;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;


public class Transaction {
    StringProperty id;
    StringProperty operationDate;
    StringProperty transactionType;
    StringProperty amount;
    StringProperty currency;
    StringProperty title;
    StringProperty balance;
    StringProperty bank;
    SimpleListProperty<String> tags;



    public Transaction(String id, String operationDate, String transactionType, String amount, String currency, String title, String balance, String bank, ObservableList<String> tags) {
        this.id = new SimpleStringProperty(id);
        this.operationDate = new SimpleStringProperty(operationDate);
        this.transactionType = new SimpleStringProperty(transactionType);
        this.amount = new SimpleStringProperty(amount);
        this.currency = new SimpleStringProperty(currency);
        this.title = new SimpleStringProperty(title);
        this.balance = new SimpleStringProperty(balance);
        this.bank = new SimpleStringProperty(bank);
        this.tags = new SimpleListProperty<>(tags);
    }

    public String getId() {
        return id.get();
    }
    public StringProperty idProperty() {
        return id;
    }
    public String getOperationDate() {
        return operationDate.get();
    }
    public StringProperty operationDateProperty() {
        return operationDate;
    }
    public String getTransactionType() {
        return transactionType.get();
    }
    public StringProperty transactionTypeProperty() {
        return transactionType;
    }
    public String getAmount() {
        return amount.get();
    }
    public StringProperty amountProperty() {
        return amount;
    }
    public String getCurrency() {
        return currency.get();
    }
    public StringProperty currencyProperty() {
        return currency;
    }
    public String getTitle() {
        return title.get();
    }
    public StringProperty titleProperty() {
        return title;
    }
    public String getBalance() {
        return balance.get();
    }
    public StringProperty balanceProperty() {
        return balance;
    }
    public String getBank() {
        return bank.get();
    }
    public StringProperty bankProperty() {
        return bank;
    }
    public ObservableList<String> getTags() {return tags.get();}
    public SimpleListProperty<String> tagsProperty() {return tags;}

    public void setId(String id) {this.id.set(id);}
    public void setTitle(String title) {this.title.set(title);}
    public void setTags(ObservableList<String> tags) {this.tags.set(tags);}
}
