package ru.webdev.em_bank.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.webdev.em_bank.models.Customer;
import ru.webdev.em_bank.repositories.CustomerRepository;

@Service
public class CustomerDetailsService implements UserDetailsService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerDetailsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Получаем пользователя из таблицы по логину с формы аутентификации
        Optional<Customer> customer = customerRepository.findByLogin(username);
        // Если пользователь не был найден
        if (customer.isEmpty()) {
            // Выбрасываем исключение что данный пользователь не найден
            // Данное исключение будет поймано Spring Security и сообщение
            //будет выведено на страницу
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        return new CustomerDetails(customer.get());
    }
}
