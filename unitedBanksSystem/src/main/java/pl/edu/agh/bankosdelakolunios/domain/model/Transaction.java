package pl.edu.agh.bankosdelakolunios.domain.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction{
    @Id
    @GeneratedValue
    private Integer id;
    private LocalDate operationDate;
    private String transactionType;
    private String amount;
    private String currency;
    private String title;
    private String balance;
    private String bank;
    @OneToMany(cascade = {CascadeType.ALL})
    private Set<Tag> tags;
}
