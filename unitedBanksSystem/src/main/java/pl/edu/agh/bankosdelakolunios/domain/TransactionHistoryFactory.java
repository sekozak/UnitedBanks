package pl.edu.agh.bankosdelakolunios.domain;

import lombok.RequiredArgsConstructor;
import pl.edu.agh.bankosdelakolunios.domain.model.Transaction;
import pl.edu.agh.bankosdelakolunios.domain.model.TransactionHistory;
import pl.edu.agh.bankosdelakolunios.domain.ports.TransactionCreator;
import pl.edu.agh.bankosdelakolunios.domain.ports.TransactionHistoryFileReader;

import java.io.File;
import java.util.List;

@RequiredArgsConstructor
public class TransactionHistoryFactory {

    private final TransactionHistoryFileReader reader;

    public TransactionHistory createTransactionHistory(File file) {
        List<List<String>> rawTransactionHistory = reader.loadTransactionHistory(file.getAbsolutePath());
        String bankPrefix = file.getName().split("_")[0];
        TransactionCreator transactionCreator;

        switch (bankPrefix) {
            case "PKOBP":
                transactionCreator = new PkoBpTransactionCreator();
                break;
            case "MIL":
                transactionCreator = new MillenniumTransactionCreator();
                break;
            default:
                throw new IllegalArgumentException("Unrecognized bank prefix");
        }

        return new TransactionHistory(convertToTransactions(rawTransactionHistory, transactionCreator));
    }

    private List<Transaction> convertToTransactions (List<List<String>> transactions, TransactionCreator transactionCreator) {
        return transactions.stream()
                .map(transactionCreator::createTransaction)
                .toList();
    }
}
