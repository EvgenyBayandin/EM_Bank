package ru.webdev.em_bank.controllers;

import java.util.Collections;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.webdev.em_bank.models.Customer;
import ru.webdev.em_bank.services.CustomerService;


@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/search")
    public List<Customer> searchCustomerByFirstnameOrLastnameAndPatronymic(
        @RequestParam(required = false) String firstname,
                @RequestParam(required = false) String lastname,
                        @RequestParam(required = false) String patronymic){
                                if (firstname!= null && lastname!= null && patronymic!= null) {
                                    return customerService.findByFirstnameOrLastnameOrPatronymic(firstname, lastname, patronymic);
                                }
                                return Collections.emptyList();
    }
}
