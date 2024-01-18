package com.changejar.kiranaregister;

import com.changejar.entity.User;
import com.changejar.entity.KiranaStore;
import com.changejar.entity.UserAction;
import com.changejar.enums.StoreStatus;
import com.changejar.repository.KiranaStoreRepository;
import com.changejar.repository.UserActionRepository;
import com.changejar.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@EntityScan("com.changejar.entity")
@EnableAutoConfiguration
@EnableJpaRepositories("com.changejar.repository")
@ComponentScan("com.changejar.*")
@EnableWebMvc
@SpringBootApplication
public class KiranaRegisterApplication {

	public static void main(String[] args) {
		SpringApplication.run(KiranaRegisterApplication.class, args);
	}

	@Bean
	CommandLineRunner initUserRepository(UserRepository userRepository) {
		return args -> {
			userRepository.saveAll(List.of(
					new User(
							null,
							"Manjunath Swamy",
							null,
							9897141414L),
					new User(
							null,
							"Kiran Thapa",
							null,
							9897242424L),
					new User(
							null,
							"Ayush",
							null,
							8410121212L),
					new User(
							null,
							"Mayank",
							null,
							8410131313L),
					new User(
							null,
							"Abhishek",
							null,
							8410141414L)));
		};
	}

	@Bean
	CommandLineRunner initKiranaRepository(KiranaStoreRepository kiranaStoreRepository) {
		return args -> {
			kiranaStoreRepository.saveAll(List.of(
					new KiranaStore(
							null,
							1L,
							null,
							null,
							560048,
							StoreStatus.OPEN),
					new KiranaStore(
							null,
							2L,
							"Dehradun",
							"Uttarakhand",
							248001,
							StoreStatus.OPEN)));
		};
	}

	@Bean
	CommandLineRunner initTransactionRepository(UserActionRepository userActionRepository) {
		return args -> {
			userActionRepository.saveAll(List.of(
					new UserAction(
							null,
							1L,
							3L,
							1100.0D,
							System.currentTimeMillis(),
							System.currentTimeMillis()),
					new UserAction(
							null,
							1L,
							4L,
							785.75D,
							System.currentTimeMillis(),
							System.currentTimeMillis()),
					new UserAction(
							null,
							1L,
							5L,
							550.0D,
							System.currentTimeMillis(),
							System.currentTimeMillis()),
					new UserAction(
							null,
							2L,
							3L,
							1000.0D,
							System.currentTimeMillis(),
							System.currentTimeMillis()),
					new UserAction(
							null,
							2L,
							5L,
							300.0D,
							System.currentTimeMillis(),
							System.currentTimeMillis())));
		};
	}
}
