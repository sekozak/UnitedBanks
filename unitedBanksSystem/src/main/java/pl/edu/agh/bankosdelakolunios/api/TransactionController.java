package pl.edu.agh.bankosdelakolunios.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.bankosdelakolunios.api.model.CreateTransactionRequest;
import pl.edu.agh.bankosdelakolunios.domain.model.Transaction;
import pl.edu.agh.bankosdelakolunios.domain.TransactionService;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Optional<Transaction>> updateTransactionTags(@PathVariable Integer transactionId, @RequestBody List<String> tags) {
        Optional<Transaction> transaction = transactionService.updateTransactionTags(transactionId, tags);
        if(transaction.isPresent()){
            return new ResponseEntity<>(transaction, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(transaction, HttpStatus.NOT_FOUND);
    }


    @GetMapping
    public ResponseEntity<Page<Transaction>> getPages(
            @PageableDefault(value = 20, page = 0) Pageable paging,
            @RequestParam(value = "tags", required = false) List<String> tags
    ){
        try {
            Page<Transaction> pagedTransactions;

            if (tags == null) pagedTransactions = transactionService.getPages(paging);
            else pagedTransactions = transactionService.getPages(tags, paging);

            return new ResponseEntity<>(pagedTransactions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tags")
    public List<String> getTags() {return transactionService.getTags();}
}
