package com.example.demo;

import com.example.demo.dto.Barber;
import com.example.demo.dto.Customer;
import com.example.demo.dto.User;
import com.example.demo.model.enums.BarberTitle;
import com.example.demo.model.enums.UserRole;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.BarberService;
import com.example.demo.service.CustomerService;
import com.example.demo.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableWebMvc
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner(AuthenticationService authenticationService) {
		return args -> {
			List<Barber> barberList = new ArrayList<>();

			barberList.add(new Barber("Stefan", BarberTitle.MASTER, new User("stefan@gmail.com", "123", UserRole.BARBER)));
			barberList.add(new Barber("Marko", BarberTitle.MASTER, new User("marko@gmail.com", "123", UserRole.BARBER)));
			barberList.add(new Barber("Ivan", BarberTitle.MASTER, new User("ivan@gmail.com", "123", UserRole.BARBER)));
			barberList.add(new Barber("Mirko", BarberTitle.MASTER, new User("mirko@gmail.com", "123", UserRole.BARBER)));

			barberList.forEach(authenticationService::registerBarber);
			authenticationService.registerCustomer(new Customer("Lazar", new User("lazar@gmail.com", "123", UserRole.CUSTOMER)));
		};
	}

}
