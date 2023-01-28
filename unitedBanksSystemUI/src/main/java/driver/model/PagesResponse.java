package driver.model;

import controller.model.TransactionResponse;
import model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class PagesResponse {
    private final List<TransactionResponse> content;
    private final Integer number;
    private final Integer totalElements;
    private final Integer totalPages;

    public PagesResponse(List<TransactionResponse> transactionsRes, Integer currentPage, Integer totalItems, Integer totalPages) {
        this.content = transactionsRes;
        this.number = currentPage;
        this.totalElements = totalItems;
        this.totalPages = totalPages;
    }

    public Pages createPage() {
        return new Pages(number, totalElements, totalPages, convertSetToList(content));
    }

    private List<Transaction> convertSetToList(List<TransactionResponse> transactionsRes){
        List<Transaction> transactions = new ArrayList<>();
        for(TransactionResponse t : transactionsRes){
            transactions.add(t.createTransaction());
        }
        return transactions;
    }


}
