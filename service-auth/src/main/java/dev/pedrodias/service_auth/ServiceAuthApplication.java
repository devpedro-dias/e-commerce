package dev.pedrodias.service_auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "dev.pedrodias.service_auth.core.repositories")
public class ServiceAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceAuthApplication.class, args);
	}

}
