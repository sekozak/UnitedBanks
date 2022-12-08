package pl.edu.agh.bankosdelakolunios.banks;

import pl.edu.agh.bankosdelakolunios.Transaction;

import java.util.List;

public interface TransactionCreator {
    Transaction createTransaction(List<String> metadata);
}
