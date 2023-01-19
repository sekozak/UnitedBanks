package driver.model;

import model.Transaction;
import java.util.List;

public class Pages {
    private List<Transaction> transactions;
    private Integer currentPage;
    private Integer totalItems;
    private Integer totalPages;

    public Pages(Integer currentPage, Integer totalItems, Integer totalPages, List<Transaction> transactions) {
        this.transactions = transactions;
        this.currentPage = currentPage;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
    }

    public List<Transaction> getTransactions() {return transactions;}
    public void setTransactions(List<Transaction> transactions) {this.transactions = transactions;}
    public Integer getCurrentPage() {return currentPage;}
    public void setCurrentPage(Integer currentPage){this.currentPage=currentPage;}
    public Integer getTotalItems() {return totalItems;}
    public void setTotalItems(Integer totalItems){this.totalPages=totalItems;}
    public Integer getTotalPages() {return totalPages;}
    public void setTotalPages(Integer totalPages) {this.totalPages = totalPages;}
}
