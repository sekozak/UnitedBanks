package pl.edu.agh.bankosdelakolunios.banks;

import pl.edu.agh.bankosdelakolunios.Transaction;

import java.time.LocalDate;
import java.util.List;

public class MilleniumTransactionCreator implements TransactionCreator{
    public Transaction createTransaction(List<String> metadata) {

        LocalDate operationDate = LocalDate.parse(metadata.get(1));
        String transactionType = metadata.get(3);
        String amount = metadata.get(7);
        String currency = metadata.get(10);
        String title = metadata.get(6);


        return Transaction.builder()
                .operationDate(operationDate)
                .transactionType(transactionType)
                .amount(amount)
                .currency(currency)
                .title(title)
                .build();
    }
}
