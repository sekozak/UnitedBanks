package pl.edu.agh.bankosdelakolunios.db;

import org.springframework.stereotype.Service;
import pl.edu.agh.bankosdelakolunios.api.model.CreateTransactionRequest;
import pl.edu.agh.bankosdelakolunios.Transaction;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction reqTransaction(CreateTransactionRequest transactionRequest){
        Transaction transaction = transactionRequest.createTransaction();
        return transactionRepository.save(transaction);
    }

    public Transaction addTransaction(Transaction transaction){
        return transactionRepository.save(transaction);
    }
}
