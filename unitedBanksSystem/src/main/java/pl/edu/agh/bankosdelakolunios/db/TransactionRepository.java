package pl.edu.agh.bankosdelakolunios.db;

import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.bankosdelakolunios.Transaction;

@Registered
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}