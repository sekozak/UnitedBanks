package controller.model;

import model.Transaction;

public class TransactionResponse {
    String id;
    String operationDate;
    String transactionType;
    String amount;
    String currency;
    String title;
    String balance;
    String bank;

    public TransactionResponse(String id, String operationDate, String transactionType, String amount, String currency, String title, String balance, String bank) {
        this.id = id;
        this.operationDate = operationDate;
        this.transactionType = transactionType;
        this.amount = amount;
        this.currency = currency;
        this.title = title;
        this.balance = balance;
        this.bank = bank;
    }


    public Transaction createTransaction() {
        return new Transaction(id, operationDate, transactionType, amount, currency, title, balance, bank);
    }

}
