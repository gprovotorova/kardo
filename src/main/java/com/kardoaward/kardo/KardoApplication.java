package com.kardoaward.kardo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.kardoaward.kardo"})
public class KardoApplication {

	public static void main(String[] args) {
		SpringApplication.run(KardoApplication.class, args);
	}

}
