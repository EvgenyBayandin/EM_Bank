package ru.webdev.em_bank.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.webdev.em_bank.models.Customer;
import ru.webdev.em_bank.models.Email;
import ru.webdev.em_bank.models.Phone;
import ru.webdev.em_bank.repositories.CustomerRepository;
import ru.webdev.em_bank.repositories.EmailRepository;
import ru.webdev.em_bank.repositories.PhoneRepository;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final PhoneRepository phoneRepository;
    private final EmailRepository emailRepository;


    @Autowired
    public CustomerService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder, PhoneRepository phoneRepository, EmailRepository emailRepository) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.phoneRepository = phoneRepository;
        this.emailRepository = emailRepository;
    }

    public Customer findByCustomerId(Customer customer) {
        Optional<Customer> customer_db = customerRepository.findById(customer.getId());
        return customer_db.orElse(null);
    }

    public Customer findByLogin(Customer customer) {
        Optional<Customer> customer_db = customerRepository.findByLogin(customer.getLogin());
        return customer_db.orElse(null);
    }

    public Customer findByEmail(Customer customer) {
        Optional<Customer> customer_db = customerRepository.findByEmail(customer.getEmail());
        return customer_db.orElse(null);
    }

    public Customer findByPhone(Customer customer) {
        Optional<Customer> customer_db = customerRepository.findByPhone(customer.getPhone());
        return customer_db.orElse(null);
    }

    public List<Customer> findByFirstnameOrLastnameOrPatronymic(String firstname, String lastname, String patronymic) {
        return customerRepository.findByFirstnameOrLastnameOrPatronymic(firstname, lastname, patronymic);
    }

    public Customer findByDateOfBirth(Customer customer) {
        Optional<Customer> customer_db = customerRepository.findByDateOfBirth(customer.getDateOfBirth());
        return customer_db.orElse(null);
    }

    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Transactional
    public void register(Customer customer) {

        if (customerRepository.existsByLogin(customer.getLogin())) {
            throw new DuplicateLoginException("Login уже занят");
        }

        if (customer.getPhones() != null) {
            for (Phone phone : customer.getPhones()) {
                if (phoneRepository.existsByPhoneAndCustomerId(phone.getPhone(), phone.getId())) {
                    throw new DuplicatePhoneException("Данный номер телефона уже занят");
                }
            }
        }

        if (customer.getEmails() != null) {
            for (Email email : customer.getEmails()) {
                if (emailRepository.existsByEmailAndCustomerId(email.getEmail(), email.getId())) {
                    throw new DuplicateEmailException("Данный email уже занят");
                }
            }
        }

        customer.setPassword(passwordEncoder.encode(customer.getPassword()));

        Customer savedCustomer = customerRepository.save(customer);

        if (customer.getPhone() != null) {
            for (Phone phones : customer.getPhones()) {
                phones.setCustomer(savedCustomer);
            }
        }

        if (customer.getEmail() != null) {
            for (Email emails : customer.getEmails()) {
                emails.setCustomer(savedCustomer);
            }
        }
    }

    public List<Phone> getPhonesByCustomerId(int id) {
        return phoneRepository.findByCustomerId(id);
    }

    public void addPhoneForCustomer(int id, String phoneNumber) {
        Customer customer = customerRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new CustomerNotFoundException("Пользователь не найден"));

        if (phoneRepository.existsByPhoneAndCustomerId(phoneNumber, id)) {
            throw new DuplicatePhoneException("Номер телефона уже занят");
        }

        Phone phone = new Phone();
        phone.setPhone(phoneNumber);
        phone.setCustomer(customer);
        phoneRepository.save(phone);
    }

    public void deletePhoneForCustomer(int id) {
        Customer customer = customerRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new CustomerNotFoundException("Пользователь не найден"));

        if (customer.getPhones().isEmpty()) {
            throw new InvalidOperationException("Нет телефонных номеров для удаления");
        }

        if (customer.getPhones().size() == 1) {
            throw new InvalidOperationException("Нельзя удалить единственный контактный номер телефона");
        }

        Phone phoneToDelete = customer.getPhones().get(0);
        customer.getPhones().remove(phoneToDelete);
        phoneRepository.delete(phoneToDelete);
    }


    public List<Email> getEmailsByCustomerId(int id) {
        return emailRepository.findByCustomerId(id);
    }

    private void findCustomerByIdAndEmail(int id, String email) {
        Customer customer = customerRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new CustomerNotFoundException("Пользователь не найден"));

        if (emailRepository.existsByEmailAndCustomerId(email, id)) {
            throw new DuplicateEmailException("Емейл уже занят");
        }

        Email emailToAdd = new Email();
        emailToAdd.setEmail(email);
        emailToAdd.setCustomer(customer);

        emailRepository.save(emailToAdd);
    }

    public void addEmailForCustomer(int id, String email) {
        findCustomerByIdAndEmail(id, email);
    }

    public void deleteEmailForCustomer(int id, int emailId) {
        Customer customer = customerRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new CustomerNotFoundException("Пользователь не найден"));

        Email email = emailRepository.findById(emailId)
                .orElseThrow(() -> new EmailNotFoundException("Емейл не найден по данному идентификатору: " + emailId));

        if (customer.getEmails().size() == 1) {
            throw new InvalidOperationException("Нельзя удалить единственный контактный емейл");
        }

        customer.getEmails().remove(email);
        emailRepository.delete(email);
    }

}
