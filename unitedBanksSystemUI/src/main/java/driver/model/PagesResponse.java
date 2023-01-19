package driver.model;

import controller.model.TransactionResponse;
import model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class PagesResponse {
    private final List<TransactionResponse> transactions;
    private final Integer currentPage;
    private final Integer totalItems;
    private final Integer totalPages;

    public PagesResponse(List<TransactionResponse> transactionsRes, Integer currentPage, Integer totalItems, Integer totalPages) {
        this.transactions = transactionsRes;
        this.currentPage = currentPage;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
    }

    public Pages createPage() {
        return new Pages(currentPage, totalItems, totalPages, convertSetToList(transactions));
    }

    private List<Transaction> convertSetToList(List<TransactionResponse> transactionsRes){
        List<Transaction> transactions = new ArrayList<>();
        for(TransactionResponse t : transactionsRes){
            transactions.add(t.createTransaction());
        }
        return transactions;
    }


    public List<TransactionResponse> getTransactionsRes() {return transactions;}
    public Integer getCurrentPage() {return currentPage;}
    public Integer getTotalItems() {return totalItems;}
    public Integer getTotalPages() {return totalPages;}
}
