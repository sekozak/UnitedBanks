package pl.edu.agh.bankosdelakolunios.banks;

import pl.edu.agh.bankosdelakolunios.Transaction;

import java.util.List;

public class Bank {
    private TransactionCreator transactionCreator;

    public Bank() {}

    public void setStrategy(TransactionCreator transactionCreator) {
        this.transactionCreator=transactionCreator;
    }

    public Transaction executeStrategy(List<String> metadata) {
        return transactionCreator.createTransaction(metadata);
    }

}
