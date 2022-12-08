package pl.edu.agh.bankosdelakolunios.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.bankosdelakolunios.api.model.CreateTransactionRequest;
import pl.edu.agh.bankosdelakolunios.Transaction;
import pl.edu.agh.bankosdelakolunios.db.TransactionService;

import java.rmi.ServerException;
import java.util.List;

@RestController
@RequestMapping(path = "/transactions")
public class TransactionController {
    private final TransactionService transactionService;
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Transaction> create(@RequestBody CreateTransactionRequest transactionRequest) throws ServerException {
        Transaction transaction = transactionService.reqTransaction(transactionRequest);
        if (transaction == null) {
            throw new ServerException("Fail to add transaction");
        } else {
            return new ResponseEntity<>(transaction, HttpStatus.CREATED);
        }
    }
    @GetMapping
    public List<Transaction> getStudents() {return transactionService.getTransactions();}

}
