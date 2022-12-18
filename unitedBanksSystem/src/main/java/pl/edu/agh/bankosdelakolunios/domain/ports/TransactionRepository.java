package pl.edu.agh.bankosdelakolunios.domain.ports;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.bankosdelakolunios.domain.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>{ }