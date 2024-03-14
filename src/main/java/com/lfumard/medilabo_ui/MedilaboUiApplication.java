package com.lfumard.medilabo_ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
//import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MedilaboUiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedilaboUiApplication.class, args);
	}

}
