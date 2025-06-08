package com.example.demo;

import com.example.demo.dto.Barber;
import com.example.demo.dto.Customer;
import com.example.demo.dto.Service;
import com.example.demo.dto.User;
import com.example.demo.dto.request_response.AuthenticationRequest;
import com.example.demo.model.enums.BarberTitle;
import com.example.demo.model.enums.UserRole;
import com.example.demo.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
@EnableWebMvc
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


//	@Bean
//	public CommandLineRunner commandLineRunner(AuthenticationService authenticationService,
//											   ServiceService serviceService) {
//		return args -> {
//			List<AuthenticationRequest> requests = new ArrayList<>();
//
//			requests.add(
//					new AuthenticationRequest(
	//							new User(UUID.randomUUID().toString(), "stefan@gmail.com", "123", UserRole.BARBER),
//							new Barber(UUID.randomUUID().toString(), "Stefan", BarberTitle.MASTER)
//					)
//			);
//
//			requests.add(
//					new AuthenticationRequest(
//							new User(UUID.randomUUID().toString(), "marko@gmail.com", "123", UserRole.BARBER),
//							new Barber(UUID.randomUUID().toString(), "Marko", BarberTitle.MASTER)
//					)
//			);
//
//			requests.add(
//					new AuthenticationRequest(
//							new User(UUID.randomUUID().toString(), "ivan@gmail.com", "123", UserRole.BARBER),
//							new Barber(UUID.randomUUID().toString(), "Ivan", BarberTitle.MASTER)
//					)
//			);
//
//			requests.add(
//					new AuthenticationRequest(
//							new User(UUID.randomUUID().toString(), "mirko@gmail.com", "123", UserRole.BARBER),
//							new Barber(UUID.randomUUID().toString(),"Mirko", BarberTitle.MASTER)
//					)
//			);
//
//			requests.forEach(authenticationRequest ->
//				authenticationService.registerBarber(authenticationRequest.barber().get(), authenticationRequest.user())
//			);
//
//			AuthenticationRequest authenticationRequest = new AuthenticationRequest(
//					new User(UUID.randomUUID().toString(), "lazar@gmail.com", "123", UserRole.CUSTOMER),
//					new Customer(UUID.randomUUID().toString(), "Lazar")
//			);
//
//			authenticationService.registerCustomer(
//					authenticationRequest.customer().get(),
//					authenticationRequest.user()
//			);
//
//			List<Service> serviceList = new ArrayList<>();
//
//			Barber barber = requests.get(0).barber().get();
//
//			serviceList.add(new Service(UUID.randomUUID().toString(), "Klasicno podsisavanje", Duration.ofMinutes(30), 1100L, barber));
//			serviceList.add(new Service(UUID.randomUUID().toString(), "Moderno podsisavanje", Duration.ofMinutes(40), 1200L,  barber));
//			serviceList.add(new Service(UUID.randomUUID().toString(), "Potpuno sisanje", Duration.ofMinutes(20), 900L,  barber));
//			serviceList.add(new Service(UUID.randomUUID().toString(), "Brijanje", Duration.ofMinutes(30), 1100L,  barber));
//			serviceList.add(new Service(UUID.randomUUID().toString(), "Brijanje glave", Duration.ofMinutes(30), 1000L,  barber));
//
//			serviceList.forEach(serviceService::addService);
//		};
//	}

}
