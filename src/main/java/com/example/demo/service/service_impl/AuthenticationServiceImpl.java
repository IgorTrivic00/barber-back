package com.example.demo.service.service_impl;

import com.example.demo.dto.Barber;
import com.example.demo.dto.Customer;
import com.example.demo.dto.User;
import com.example.demo.dto.UserSession;
import com.example.demo.model.UserEntity;
import com.example.demo.model.enums.UserRole;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtService;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.BarberService;
import com.example.demo.service.CustomerService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private static Logger log = Logger.getLogger(AuthenticationServiceImpl.class.getName());

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final CustomerService customerService;
    private final BarberService barberService;
    private final UserRepository userRepository;

    @Autowired
    public AuthenticationServiceImpl(UserService userService,
                                     AuthenticationManager authenticationManager,
                                     JwtService jwtService,
                                     PasswordEncoder passwordEncoder,
                                     CustomerService customerService,
                                     BarberService barberService,
                                     UserRepository userRepository) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.customerService = customerService;
        this.barberService = barberService;
        this.userRepository = userRepository;
    }

    @Override
    public UserSession login(User user) {
        String password = user.password()
                .orElseThrow(() -> new IllegalArgumentException("Nema lozinke!")).trim();

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.email().trim(),
                        password
                )
        );
        User user1 = userService.findByEmail(user.email().trim());
        String token = jwtService.generateToken(new UserEntity(user1.email().trim(), password, user1.userRole()));

        if(user1.userRole().equals(UserRole.BARBER)) {
            Barber barber = barberService.findByUser(user);
            return new UserSession(user1, barber, token);
        } else if (user1.userRole().equals(UserRole.CUSTOMER)) {
            Customer customer = customerService.findByUser(user);
            return new UserSession(user1, customer, token);
        }

        return null;
    }

    @Override
    public Customer registerCustomer(Customer customer) {
        User user = new User(customer.user().email().trim(), passwordEncoder.encode(customer.user().password()
                .orElseThrow(() -> new IllegalArgumentException("Nema lozinke!")).trim()), UserRole.CUSTOMER);

        User user1 = userService.save(user);

        UserEntity userEntity = userRepository.findByEmail(user1.email().trim())
                .orElseThrow(() -> new IllegalArgumentException("Korisnik ne postoji!"));

        return customerService.save(customer, userEntity);
    }

    @Override
    public Barber registerBarber(Barber barber) {
        User user = new User(barber.user().email().trim(), passwordEncoder.encode(barber.user().password()
                .orElseThrow(() -> new IllegalArgumentException("Nema lozinke!")).trim()), UserRole.BARBER);

        User user1 = userService.save(user);

        UserEntity userEntity = userRepository.findByEmail(user1.email().trim())
                .orElseThrow(() -> new IllegalArgumentException("Korisnik ne postoji!"));

        return barberService.save(barber, userEntity);
    }
}
