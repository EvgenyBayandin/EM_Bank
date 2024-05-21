package ru.webdev.em_bank.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.webdev.em_bank.models.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {
    List<Email> findByCustomerId(Long customerId);
    boolean existsByEmailAndCustomerId(String email, Long customerId);
}
