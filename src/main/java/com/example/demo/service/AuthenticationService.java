package com.example.demo.service;

import com.example.demo.dto.Barber;
import com.example.demo.dto.Customer;
import com.example.demo.dto.User;
import com.example.demo.dto.UserSession;

public interface AuthenticationService {

    UserSession login(User user);

    Customer registerCustomer(Customer customer, User user);

    Barber registerBarber(Barber barber, User user);
}
