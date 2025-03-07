package dev.pedrodias.service_auth.core.service;

import dev.pedrodias.service_auth.core.document.Event;
import dev.pedrodias.service_auth.core.document.User;
import dev.pedrodias.service_auth.core.dto.SignUpDto;
import dev.pedrodias.service_auth.core.producer.SagaProducer;
import dev.pedrodias.service_auth.core.repositories.EventRepository;
import dev.pedrodias.service_auth.core.utils.HashingUtil;
import dev.pedrodias.service_auth.core.utils.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class SignUpService {

    private static final String TRANSACTION_ID_PATTERN = "%s_%s";

    private final EventRepository eventRepository;
    private final SagaProducer producer;
    private final JsonUtil jsonUtil;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private HashingUtil hashingUtil;

    public User createUser(SignUpDto signUpDto) {
        String emailSalt = hashingUtil.generateSalt();
        String cpfSalt = hashingUtil.generateSalt();

        var user = User.builder()
                .id(UUID.randomUUID().toString())
                .name(signUpDto.getName())
                .cpf(hashingUtil.generateHash(signUpDto.getCpf(), cpfSalt))
                .email(hashingUtil.generateHash(signUpDto.getEmail(), emailSalt))
                .addresses(signUpDto.getAddresses())
                .cellphone(signUpDto.getCellphone())
                .password(encryptPassword(signUpDto.getPassword()))
                .userRole(signUpDto.getRole())
                .transactionId(generateTransactionId())
                .build();

        publishEvent(user);
        return user;
    }

    private Event createPayload(User user) {
        var event = Event
                .builder()
                .userId(user.getId())
                .transactionId(user.getTransactionId())
                .payload(user)
                .createdAt(LocalDateTime.now())
                .build();
        eventRepository.save(event);
        return event;
    }

    private void publishEvent(User user) {
        producer.sendEvent(jsonUtil.toJson(createPayload(user)));
    }

    private String generateTransactionId() {
        return String.format(TRANSACTION_ID_PATTERN, Instant.now().toEpochMilli(), UUID.randomUUID());
    }

    public String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
