package pl.edu.agh.bankosdelakolunios.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Tag {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    public Tag(String name) {
        this.name = name;
    }

}
