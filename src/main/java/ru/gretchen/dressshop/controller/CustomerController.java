package ru.gretchen.dressshop.controller;

import ru.gretchen.dressshop.exception.CustomerNotFoundException;
import ru.gretchen.dressshop.exception.GetCustomerException;
import ru.gretchen.dressshop.exception.RepositoryInitializeException;
import ru.gretchen.dressshop.model.CustomerEntity;
import ru.gretchen.dressshop.repository.CustomerRepository;

import java.util.List;

public class CustomerController {
    private final CustomerRepository customerRepository;

    public CustomerController() throws RepositoryInitializeException {
        this.customerRepository = new CustomerRepository();
    }

    public CustomerEntity getCustomer(Long id) {
        try {
            return customerRepository.getById(id);
        } catch (CustomerNotFoundException e) {
            throw new GetCustomerException(e.getMessage());
        }
    }

    public List<CustomerEntity> getAllCustomers() {
        return customerRepository.getAll();
    }

    public CustomerEntity createCustomer(CustomerEntity customer) {
        return customerRepository.save(customer);
    }

    public CustomerEntity updateCustomer(Long id, CustomerEntity customer) {
        return customerRepository.update(id, customer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.delete(id);
    }
}
