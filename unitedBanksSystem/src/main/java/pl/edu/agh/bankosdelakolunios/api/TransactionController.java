package pl.edu.agh.bankosdelakolunios.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.bankosdelakolunios.api.model.CreateTransactionRequest;
import pl.edu.agh.bankosdelakolunios.domain.model.PagesResponse;
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
    public ResponseEntity<PagesResponse> getPages(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "3") Integer size,
            @RequestParam(value = "tags", required = false) List<String> tags
    ){
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<Transaction> pagedTransactions;

            if (tags == null) pagedTransactions = transactionService.getPages(paging);
            else pagedTransactions = transactionService.getPages(tags, paging);

            PagesResponse pageResponse = new PagesResponse(
                    pagedTransactions.getContent(),
                    pagedTransactions.getNumber(),
                    (int) pagedTransactions.getTotalElements(),
                    pagedTransactions.getTotalPages()
            );

            return new ResponseEntity<>(pageResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tags")
    public List<String> getTags() {return transactionService.getTags();}
}
