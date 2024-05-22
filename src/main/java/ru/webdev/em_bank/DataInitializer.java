//package ru.webdev.em_bank;
//
//import java.math.BigDecimal;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//import ru.webdev.em_bank.models.Account;
//import ru.webdev.em_bank.models.Customer;
//import ru.webdev.em_bank.models.Email;
//import ru.webdev.em_bank.models.Phone;
//import ru.webdev.em_bank.repositories.CustomerRepository;
//
//import java.util.Arrays;
//
//@Component
//public class DataInitializer implements CommandLineRunner {
//    private final CustomerRepository customerRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    public DataInitializer(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
//        this.customerRepository = customerRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        // Создаем и сохраняем начальных пользователей
//        Customer customer1 = new Customer(
//                "user1",
//                passwordEncoder.encode("password1"),
//                "user1@example.com",
//                "1234567890",
//                "John",
//                "Doe",
//                "Johnovich",
//                "1990-01-01",
//                Arrays.asList(new Phone("1234567891", null), new Phone("1234567892", null)),
//                Arrays.asList(new Email("user1@example.org", null)),
//                new Account(new BigDecimal("1000.00"))
//        );
//        customerRepository.save(customer1);
//
//        Customer customer2 = new Customer(
//                "user2",
//                passwordEncoder.encode("password2"),
//                "user2@example.com",
//                "0987654321",
//                "Jane",
//                "Smith",
//                null,
//                "1995-05-15",
//                Arrays.asList(new Phone("0987654322", null)),
//                Arrays.asList(new Email("jane@example.org", null), new Email("jane.smith@example.com", null)),
//                new Account(new BigDecimal("2500.75"))
//        );
//        customerRepository.save(customer2);
//
////        Account account1 = customer1.getAccount();
////        account1.setBalance(new BigDecimal("1000.00"));
////        customer1.setAccount(account1);
//
////        Account account2 = customer2.getAccount();
////        account2.setBalance(new BigDecimal("2500.75"));
////        customer2.setAccount(account2);
//    }
//
//}