package dev.pedrodias.service_auth.core.repositories;

import dev.pedrodias.service_auth.core.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "service-user", url = "http://localhost:8081/users")
public interface UserClient {
    @GetMapping("/find-by-login")
    UserDto findByLogin(@RequestParam String login);
}
