package pl.edu.agh.bankosdelakolunios.api.model;

import lombok.Getter;
import lombok.Setter;
import pl.edu.agh.bankosdelakolunios.Transaction;
import java.time.LocalDate;

@Getter
@Setter
public class CreateTransactionRequest{
    LocalDate operationDate;
    String transactionType;
    String amount;
    String currency;
    String title;

    public CreateTransactionRequest(LocalDate operationDate, String transactionType, String amount, String currency, String title) {
        this.operationDate = operationDate;
        this.transactionType = transactionType;
        this.amount = amount;
        this.currency = currency;
        this.title = title;
    }

    public Transaction createTransaction(){
        return Transaction.builder()
                .operationDate(operationDate)
                .transactionType(transactionType)
                .amount(amount)
                .currency(currency)
                .title(title)
                .build();
    }


    @Override
    public String toString() {
        return "CreateTransactionRequest{" +
                "operationDate=" + operationDate +
                ", transactionType='" + transactionType + '\'' +
                ", amount='" + amount + '\'' +
                ", currency='" + currency + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
