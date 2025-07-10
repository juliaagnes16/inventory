package com.example.demo;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Log4j2
@SpringBootApplication
public class InventoryApplication {

	public static void main(String[] args) {
		log.info("Aplikasi jalan dengan Log4j2 aktif!");
		SpringApplication.run(InventoryApplication.class, args);
	}

}
