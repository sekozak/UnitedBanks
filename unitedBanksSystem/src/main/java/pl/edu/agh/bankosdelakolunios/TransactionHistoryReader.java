package pl.edu.agh.bankosdelakolunios;

import pl.edu.agh.bankosdelakolunios.banks.Bank;
import pl.edu.agh.bankosdelakolunios.banks.MilleniumTransactionCreator;
import pl.edu.agh.bankosdelakolunios.banks.PkoBpTransactionCreator;

import java.util.ArrayList;
import java.util.List;

public class TransactionHistoryReader {
    public Bank bank = new Bank();
    public List<Transaction> readFromFile(String absolutePathToFile, String fileType) {


        List<Transaction> transactions = new ArrayList<>();
        List<List<String>> rawCSVData = CSVReader.readFromCSV(absolutePathToFile);

        if(fileType.equals("Pko")){
            this.bank.setStrategy(new PkoBpTransactionCreator());
        }
        else if(fileType.equals("Mil")){
            this.bank.setStrategy(new MilleniumTransactionCreator());
        }
        else{
            return transactions;
        }

        rawCSVData.forEach(record -> transactions.add(bank.executeStrategy(record)));

        return transactions;
    };
}
