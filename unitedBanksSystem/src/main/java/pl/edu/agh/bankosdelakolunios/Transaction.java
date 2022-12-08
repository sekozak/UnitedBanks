package pl.edu.agh.bankosdelakolunios;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Getter
public class Transaction{
    @Id
    @GeneratedValue
    Integer id;
    LocalDate operationDate;
    String transactionType;
    String amount;
    String currency;
    String title;

    protected Transaction() {}
    private Transaction(LocalDate operationDate, String transactionType, String amount, String currency, String title) {
        this.operationDate = operationDate;
        this.transactionType = transactionType;
        this.amount = amount;
        this.currency = currency;
        this.title = title;
    }


    public static TransactionBuilder builder() {
        return new TransactionBuilder();
    }

    public static class TransactionBuilder {
        LocalDate operationDate;
        String transactionType;
        String amount;
        String currency;
        String title;

        public TransactionBuilder operationDate(LocalDate operationDate) {
            this.operationDate = operationDate;
            return this;
        }
        public TransactionBuilder transactionType(String transactionType) {
            this.transactionType = transactionType;
            return this;
        }
        public TransactionBuilder amount(String amount) {
            this.amount = amount;
            return this;
        }
        public TransactionBuilder currency(String currency) {
            this.currency = currency;
            return this;
        }
        public TransactionBuilder title(String title) {
            this.title = title;
            return this;
        }

        public Transaction build() {
            return new Transaction(operationDate, transactionType, amount, currency, title);
        }
    }

}
