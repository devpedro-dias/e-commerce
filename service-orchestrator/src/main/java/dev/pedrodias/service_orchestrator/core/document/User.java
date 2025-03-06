package dev.pedrodias.service_orchestrator.core.document;

import dev.pedrodias.service_orchestrator.core.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class User {
    private String id;
    private String name;
    private String email;
    private String cpf;
    private String password;
    private List<String> addresses;
    private String cellphone;
    private UserRole userRole;
}
