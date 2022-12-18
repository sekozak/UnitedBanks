package pl.edu.agh.bankosdelakolunios.domain.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction{
    @Id
    @GeneratedValue
    Integer id;
    LocalDate operationDate;
    String transactionType;
    String amount;
    String currency;
    String title;
    String balance;
    String bank;
}
