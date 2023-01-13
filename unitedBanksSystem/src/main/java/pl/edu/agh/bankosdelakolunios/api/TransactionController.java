package pl.edu.agh.bankosdelakolunios.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.bankosdelakolunios.api.model.CreateTransactionRequest;
import pl.edu.agh.bankosdelakolunios.domain.model.Transaction;
import pl.edu.agh.bankosdelakolunios.domain.TransactionService;

import java.util.List;

@RestController
@RequestMapping(path = "/transactions")
public class TransactionController {
    private final TransactionService transactionService;
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Transaction> create(@RequestBody CreateTransactionRequest transactionRequest) {
        Transaction transaction = transactionService.saveTransaction(transactionRequest.createTransaction());
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }
    @PostMapping("/{transactionId}/tags")
    public ResponseEntity<Transaction> updateTransactionTags(@PathVariable Integer transactionId, @RequestBody List<String> tags) {
        Transaction transaction = transactionService.updateTransactionTags(transactionId, tags);
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Transaction> getTransactions() {return transactionService.getTransactions();}
    @GetMapping("/tags")
    public List<Transaction> getTaggedTransactions(@RequestParam List<String> tags) {return transactionService.getTaggedTransactions(tags);}

}
