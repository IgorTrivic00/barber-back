package com.example.demo.service.service_impl;

import com.example.demo.dto.Customer;
import com.example.demo.dto.User;
import com.example.demo.model.CustomerEntity;
import com.example.demo.model.UserEntity;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        CustomerEntity customerEntity = new CustomerEntity(customer.uuid(), customer.name().trim(), userEntity);
        return customerRepository.save(customerEntity).getDto();
    }

    @Override
    public Customer findByUser(User user) {
        UserEntity userEntity = userRepository.findByUuid(user.uuid())
                .orElseThrow(() -> new UsernameNotFoundException("Korisnik ne postoji!"));
        return customerRepository.findByUserEntity(userEntity)
                .orElseThrow(() -> new UsernameNotFoundException("Korisnik ne postoji!"))
                .getDto();
    }

    @Override
    public Customer update(Customer customer) {
        CustomerEntity customerEntity = customerRepository.findByUuid(customer.uuid())
                .orElseThrow(() -> new UsernameNotFoundException("Korisnik ne postoji!"));

        customerEntity = customerEntity.update(Optional.of(customer.name()), customer.mobile());
        return customerRepository.save(customerEntity).getDto();
    }
}
