package com.changejar.kiranaregister;

import com.changejar.entity.User;
import com.changejar.entity.KiranaStore;
import com.changejar.entity.Transaction;
import com.changejar.enums.CurrencyType;
import com.changejar.enums.StoreStatus;
import com.changejar.enums.TransactionType;
import com.changejar.repository.KiranaStoreRepository;
import com.changejar.repository.TransactionRepository;
import com.changejar.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDateTime;
import java.util.List;

@EntityScan("com.changejar.entity")
@EnableAutoConfiguration
@EnableJpaRepositories("com.changejar.repository")
@ComponentScan("com.changejar.*")
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
	CommandLineRunner initTransactionRepository(TransactionRepository transactionRepository) {
		return args -> {
			transactionRepository.saveAll(List.of(
					new Transaction(
							null,
							1L,
							3L,
							LocalDateTime.now().minusHours(8),
							TransactionType.CREDIT,
							CurrencyType.INR,
							1100.0D,
							1100.0D),
					new Transaction(
							null,
							1L,
							4L,
							LocalDateTime.now().minusHours(5),
							TransactionType.CREDIT,
							CurrencyType.INR,
							785.75D,
							250.0D),
					new Transaction(
							null,
							1L,
							5L,
							LocalDateTime.now(),
							TransactionType.CREDIT,
							CurrencyType.INR,
							550.0D,
							300.0D),
					new Transaction(
							null,
							2L,
							3L,
							LocalDateTime.now().minusHours(7),
							TransactionType.CREDIT,
							CurrencyType.INR,
							1000.0D,
							500.0D),
					new Transaction(
							null,
							2L,
							5L,
							LocalDateTime.now().minusHours(1),
							TransactionType.CREDIT,
							CurrencyType.INR,
							300.0D,
							300.0D)));
		};
	}
}
