package pl.edu.agh.bankosdelakolunios.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.agh.bankosdelakolunios.domain.TransactionService;
import pl.edu.agh.bankosdelakolunios.domain.ports.TransactionRepository;
import pl.edu.agh.bankosdelakolunios.storage.CSVReader;

@Configuration
public class TransactionConfig {

    @Bean
    public TransactionService transactionService(TransactionRepository transactionRepository) {
        return new TransactionService(transactionRepository, new CSVReader());
    }
}
