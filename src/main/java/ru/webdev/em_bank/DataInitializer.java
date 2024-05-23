package ru.webdev.em_bank;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.webdev.em_bank.models.Account;
import ru.webdev.em_bank.models.Customer;
import ru.webdev.em_bank.models.Email;
import ru.webdev.em_bank.models.Phone;
import ru.webdev.em_bank.repositories.CustomerRepository;

import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Создаем и сохраняем начальных пользователей
        Customer customer1 = new Customer(
                "user1",
                passwordEncoder.encode("password1"),
                "user1@example.com",
                "1234567890",
                "John",
                "Doe",
                "Johnovich",
                LocalDate.parse("1990-01-01"),
                null,
                null,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        Account account1 = new Account(new BigDecimal("1000.00"));
        customer1.setEmails(Arrays.asList(new Email("user1@example.org", customer1)));
        customer1.setPhones(Arrays.asList(new Phone("1234567891", customer1), new Phone("1234567892", customer1)));
        account1.setCustomer(customer1);
        customer1.setAccount(account1);
        customerRepository.save(customer1);

        Customer customer2 = new Customer(
                "user2",
                passwordEncoder.encode("password2"),
                "user2@example.com",
                "0987654321",
                "Jane",
                "Smith",
                null,
                LocalDate.parse("1995-05-15"),
                null,
                null,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        Account account2 = new Account(new BigDecimal("2500.75"));
        customer2.setEmails(Arrays.asList(new Email("jane.smith@example.com", customer2)));
        customer2.setPhones(Arrays.asList(new Phone("0987654322", customer2)));
        account2.setCustomer(customer2);
        customer2.setAccount(account2);
        customerRepository.save(customer2);
    }
}
