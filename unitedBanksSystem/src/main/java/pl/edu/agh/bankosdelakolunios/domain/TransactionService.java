package pl.edu.agh.bankosdelakolunios.domain;

import pl.edu.agh.bankosdelakolunios.domain.model.Transaction;
import pl.edu.agh.bankosdelakolunios.domain.ports.TransactionHistoryFileReader;
import pl.edu.agh.bankosdelakolunios.domain.ports.TransactionRepository;
import pl.edu.agh.bankosdelakolunios.domain.model.TransactionHistory;

import java.io.File;
import java.util.List;

public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionHistoryFactory transactionHistoryFactory;

    public TransactionService(TransactionRepository transactionRepository, TransactionHistoryFileReader transactionHistoryFileReader) {
        this.transactionRepository = transactionRepository;
        this.transactionHistoryFactory = new TransactionHistoryFactory(transactionHistoryFileReader);
    }

    public void uploadTransactions(File file) {
        TransactionHistory transactionHistory = transactionHistoryFactory.createTransactionHistory(file);
        transactionRepository.saveAll(transactionHistory.transactions());
        System.out.println("Transactions saved: ");
        transactionHistory.transactions().forEach(System.out::println);
    }

    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction saveTransaction(Transaction transaction){
        return transactionRepository.save(transaction);
    }
}
