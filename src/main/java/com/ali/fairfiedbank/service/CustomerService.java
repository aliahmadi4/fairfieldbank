package com.ali.fairfiedbank.service;

import com.ali.fairfiedbank.model.Customer;
import com.ali.fairfiedbank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public Page<Customer> getCustomers(int pageNo) {
        return customerRepository.findAll(PageRequest.of(pageNo, 5, Sort.by("lastName")));
    }

    public Customer getCustomerById(long id){
        return customerRepository.findById(id).orElse(null);
    }

}
