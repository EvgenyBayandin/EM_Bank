package ru.webdev.em_bank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import ru.webdev.em_bank.models.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
