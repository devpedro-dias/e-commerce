package dev.pedrodias.serviceauth.core.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class SignUpEventPayload {

    private String id;
    private String name;
    private String email;
    private String cpf;
    private String password;
    private List<String> addresses;
    private String cellphone;
}
