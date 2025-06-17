package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class BarberBackoffice {

	public static void main(String[] args) {
		SpringApplication.run(BarberBackoffice.class, args);
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
