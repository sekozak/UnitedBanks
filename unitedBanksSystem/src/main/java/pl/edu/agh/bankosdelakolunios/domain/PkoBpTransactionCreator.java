package pl.edu.agh.bankosdelakolunios.domain;

import pl.edu.agh.bankosdelakolunios.domain.model.Transaction;
import pl.edu.agh.bankosdelakolunios.domain.model.Bank;
import pl.edu.agh.bankosdelakolunios.domain.ports.TransactionCreator;

import java.time.LocalDate;
import java.util.List;

public class PkoBpTransactionCreator implements TransactionCreator {
    public Transaction createTransaction(List<String> metadata) {

        LocalDate operationDate = LocalDate.parse(metadata.get(0));
        String transactionType = metadata.get(2);
        String amount = metadata.get(3);
        String currency = metadata.get(4);
        String title = metadata.get(6);
        String balance = metadata.get(5);
        String bank = Bank.PKOBP.name();


        return Transaction.builder()
                .operationDate(operationDate)
                .transactionType(transactionType)
                .amount(amount)
                .currency(currency)
                .title(title)
                .balance(balance)
                .bank(bank)
                .build();
    }
}
