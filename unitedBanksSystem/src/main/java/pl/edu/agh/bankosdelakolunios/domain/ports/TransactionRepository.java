package pl.edu.agh.bankosdelakolunios.domain.ports;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.edu.agh.bankosdelakolunios.domain.model.Transaction;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

    Page<Transaction> findAll(Pageable pageable);
    @Query(value = "SELECT t FROM Transaction t LEFT JOIN t.tags tg WHERE tg.name IN :tagList" + " GROUP BY t HAVING COUNT(tg) = :tagListSize")
    Page<Transaction> findAllTaggedPages(@Param("tagList") List<String> tags, @Param("tagListSize") long tagListSize, Pageable pageable);

}