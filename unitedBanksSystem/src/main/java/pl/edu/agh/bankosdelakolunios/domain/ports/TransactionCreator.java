package pl.edu.agh.bankosdelakolunios.domain.ports;

import pl.edu.agh.bankosdelakolunios.domain.model.Transaction;

import java.util.List;

public interface TransactionCreator {
    Transaction createTransaction(List<String> metadata);
}
