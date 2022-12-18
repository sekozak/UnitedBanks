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
        if(bankPrefix.equals("PKOBP")){
            return new TransactionHistory(convertToTransactions(rawTransactionHistory, new PkoBpTransactionCreator()));
        }
        else if(bankPrefix.equals("MIL")){
            return new TransactionHistory(convertToTransactions(rawTransactionHistory, new MillenniumTransactionCreator()));
        } else {
            throw new IllegalArgumentException("Unrecognized bank prefix");
        }
    }

    private List<Transaction> convertToTransactions (List<List<String>> transactions, TransactionCreator transactionCreator) {
        return transactions.stream()
                .map(transactionCreator::createTransaction)
                .toList();
    }
}
