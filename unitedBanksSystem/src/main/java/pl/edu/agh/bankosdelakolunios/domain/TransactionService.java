package pl.edu.agh.bankosdelakolunios.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.edu.agh.bankosdelakolunios.domain.model.*;
import pl.edu.agh.bankosdelakolunios.domain.ports.TransactionHistoryFileReader;
import pl.edu.agh.bankosdelakolunios.domain.ports.TransactionRepository;

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


    public Page<Transaction> getPages(Pageable paging) { return transactionRepository.findAll(paging); }
    public Page<Transaction> getPages(List<String> tags, Pageable paging) {return transactionRepository.findAllTaggedPages(tags, tags.size(), paging);}

    public Transaction saveTransaction(Transaction transaction){
        return transactionRepository.save(transaction);
    }
    public Transaction updateTransactionTags(Integer transactionId, List<String> tags){
        Transaction transaction = transactionRepository.findById(transactionId).orElseThrow(() -> new IllegalStateException("Could not find transaction"));
        transaction.getTags().clear();
        tags.forEach(tag -> {
            transaction.getTags().add(new Tag(tag));
        });
        return transactionRepository.save(transaction);
    }

    public List<String> getTags() {
        return new TagList().getTags();
    }
}
