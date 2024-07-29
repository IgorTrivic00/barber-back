package com.example.demo.service;

import com.example.demo.dto.Barber;
import com.example.demo.dto.Customer;
import com.example.demo.dto.User;
import com.example.demo.model.UserEntity;

public interface CustomerService {

    Customer save(Customer customer, UserEntity userEntity);

    Customer findByUser(User user);

    Customer update(Customer customer);
}
