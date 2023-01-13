package pl.edu.agh.bankosdelakolunios.domain.ports;

import pl.edu.agh.bankosdelakolunios.domain.model.TransactionHistory;

import java.util.List;

public interface TransactionHistoryFileReader {

    List<List<String>> loadTransactionHistory(String filePath);
}
