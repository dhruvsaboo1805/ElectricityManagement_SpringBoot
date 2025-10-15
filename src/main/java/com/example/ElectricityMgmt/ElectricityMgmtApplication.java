package com.example.ElectricityMgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ElectricityMgmtApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElectricityMgmtApplication.class, args);
	}

}
