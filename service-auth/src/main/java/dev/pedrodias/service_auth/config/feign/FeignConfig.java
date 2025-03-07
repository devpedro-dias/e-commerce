package dev.pedrodias.service_auth.config.feign;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "dev.pedrodias.serviceauth.core.repositories")
public class FeignConfig {
}
