package ru.webdev.em_bank.services;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.webdev.em_bank.models.Customer;
import java.util.Collection;
import java.util.Collections;

public class CustomerDetails implements UserDetails {
    private final Customer customer;

    public CustomerDetails(Customer customer) {
        this.customer = customer;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Здесь вы можете определить роли и права доступа для пользователя
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return customer.getPassword();
    }

    @Override
    public String getUsername() {
        return customer.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Или логика проверки срока действия учетной записи
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Или логика проверки блокировки учетной записи
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Или логика проверки срока действия учетных данных
    }

    @Override
    public boolean isEnabled() {
        return true; // Или логика проверки активности учетной записи
    }
}