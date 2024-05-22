package ru.webdev.em_bank.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.webdev.em_bank.models.Phone;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Integer> {
    List<Phone> findByCustomerId(int id);

    boolean existsByPhoneAndCustomerId(String phone, int id);
}
