package com.example.demo.service.service_impl;

import com.example.demo.dto.Customer;
import com.example.demo.dto.User;
import com.example.demo.model.CustomerEntity;
import com.example.demo.model.UserEntity;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository,
                               UserRepository userRepository) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Customer save(Customer customer, UserEntity userEntity) {
        CustomerEntity customerEntity = new CustomerEntity(customer.name().trim(), customer.mobile().get().trim(), userEntity);
        return customerRepository.save(customerEntity).getDto();
    }

    @Override
    public Customer findByUser(User user) {
        UserEntity userEntity = userRepository.findByEmail(user.email())
                .orElseThrow(() -> new IllegalArgumentException("Korisnik ne postoji!"));
        return customerRepository.findByUserEntity(userEntity).getDto();
    }

    @Override
    public Customer update(Customer customer) {
        CustomerEntity customerEntity = customerRepository.findByUuid(customer.uuid().get())
                .orElseThrow(() -> new IllegalArgumentException("Korisnik ne postoji!"))
                .update(customer.name());
        return customerRepository.save(customerEntity).getDto();
    }
}
