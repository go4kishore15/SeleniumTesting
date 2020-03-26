package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
public class TPayAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TPayAppApplication.class, args);
	}
	
	
//	@Bean
//	public CommandLineRunner test(TxrService txrService) {
//		return args->{
//			txrService.txr(100.00, "1", "2");
//		};
//	}

}
