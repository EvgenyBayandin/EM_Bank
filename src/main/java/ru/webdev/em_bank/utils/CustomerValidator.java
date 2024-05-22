package ru.webdev.em_bank.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.webdev.em_bank.models.Customer;
import ru.webdev.em_bank.services.CustomerService;

@org.springframework.stereotype.Component
public class CustomerValidator implements Validator {
    private final CustomerService customerService;

    @Autowired
    public CustomerValidator(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Customer.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Customer customer = (Customer)target;
        if(customerService.findByLogin(customer) != null){
            errors.rejectValue("login", "", "Данный login уже занят");
        }
    }

}
