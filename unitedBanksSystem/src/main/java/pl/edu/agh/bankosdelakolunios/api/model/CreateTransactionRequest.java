package pl.edu.agh.bankosdelakolunios.api.model;

import lombok.Data;
import pl.edu.agh.bankosdelakolunios.domain.model.Transaction;

import java.time.LocalDate;


@Data
public class CreateTransactionRequest{
    LocalDate operationDate;
    String transactionType;
    String amount;
    String currency;
    String title;
    String balance;
    String bank;

    public CreateTransactionRequest(LocalDate operationDate, String transactionType, String amount, String currency, String title, String balance, String bank) {
        this.operationDate = operationDate;
        this.transactionType = transactionType;
        this.amount = amount;
        this.currency = currency;
        this.title = title;
        this.bank = bank;
        this.balance = balance;
    }

    public Transaction createTransaction(){
        return Transaction.builder()
                .operationDate(operationDate)
                .transactionType(transactionType)
                .amount(amount)
                .currency(currency)
                .title(title)
                .bank(bank)
                .balance(balance)
                .build();
    }


}
