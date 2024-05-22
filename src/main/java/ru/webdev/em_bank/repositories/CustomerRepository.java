package ru.webdev.em_bank.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.webdev.em_bank.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByCustomerId(long id);

    Optional<Customer> findByLogin(String login);

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByPhone(String phone);

    List<Customer> findByFirstnameOrLastnameOrPatronymic(String firstname, String lastname, String patronymic);

    Optional<Customer> findByDateOfBirth(String dateOfBirth);

    boolean existsByLogin(String login);


    // Метод для добавления  номера телефона пользователя
    @Modifying
    @Query("UPDATE Customer c SET c.phones = :phone WHERE c.id = :id AND (:newPhone IS NULL OR c.phones <> :newPhone)")
    int addPhone(@Param("id") long id, @Param("phone") String phone, @Param("newPhone") String newPhone);

    // Метод для добавления или обновления электронной почты пользователя
    @Modifying
    @Query("UPDATE Customer c SET c.emails = :email WHERE c.id = :id AND (:newEmail IS NULL OR c.emails <> :newEmail)")
    String addEmail(@Param("id") long id, @Param("email") String email, @Param("newEmail") String newEmail);

    // Метод для удаления номера телефона пользователя
    @Modifying
    @Query("UPDATE Customer c SET c.phones = NULL WHERE c.id = :id AND c.phones = :phone")
    String deletePhone(@Param("id") long id, @Param("phone") String phone);

    // Метод для удаления электронной почты пользователя
    @Modifying
    @Query("UPDATE Customer c SET c.emails = NULL WHERE c.id = :id AND c.emails = :email")
    String deleteEmail(@Param("id") long id, @Param("email") String email);

    // Метод для проверки, является ли номер телефона уникальным
    boolean isPhoneUnique(@Param("id") long id, @Param("phone") String phone);

    // Метод для проверки, является ли электронная почта уникальной
    boolean isEmailUnique(@Param("id") long id, @Param("email") String email);

}
