package pl.edu.agh.bankosdelakolunios.api.model;

import pl.edu.agh.bankosdelakolunios.domain.model.Transaction;

import java.time.LocalDate;

public record CreateTransactionRequest(LocalDate operationDate, String transactionType, String amount, String currency, String title, String balance, String bank) {

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
