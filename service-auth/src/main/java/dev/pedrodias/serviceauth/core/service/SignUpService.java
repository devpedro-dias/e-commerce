package dev.pedrodias.serviceauth.core.service;

import dev.pedrodias.serviceauth.core.document.Event;
import dev.pedrodias.serviceauth.core.document.User;
import dev.pedrodias.serviceauth.core.dto.SignUpDto;
import dev.pedrodias.serviceauth.core.utils.HashingUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
@NoArgsConstructor
public class SignUpService {

    private static final String TRANSACTION_ID_PATTERN = "%s_%s";

    private KafkaTemplate<String, Event> kafkaTemplate;

    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private HashingUtil hashingUtil;

    public void processSignUpAndPublishEvent(SignUpDto signUpDto) {
        try {
            Event event = createSignUpEvent(signUpDto);

            kafkaTemplate.send("startTopicSaga", event);

        } catch (Exception e) {
            throw new RuntimeException("Error processing sign up");
        }
    }

    private Event createSignUpEvent(SignUpDto signUpDto) {
        String encryptedPassword = encryptPassword(signUpDto.getPassword());
        String emailSalt = hashingUtil.generateSalt();
        String cpfSalt = hashingUtil.generateSalt();

        String encryptedEmail = hashingUtil.generateHash(signUpDto.getEmail(), emailSalt);
        String encryptedCpf = hashingUtil.generateHash(signUpDto.getCpf(), cpfSalt);

        User payload = new User(
                UUID.randomUUID().toString(),
                signUpDto.getName(),
                encryptedEmail,
                encryptedCpf,
                encryptedPassword,
                signUpDto.getAddresses(),
                signUpDto.getCellphone(),
                signUpDto.getRole()
        );

        return Event.builder()
                .transactionId(
                        String.format(TRANSACTION_ID_PATTERN, Instant.now().toEpochMilli(), UUID.randomUUID())
                )
                .payload(payload)
                .source("service-auth")
                .build();
    }

    public void notifyEnding(Event event) {
        event.setId(event.getPayload().getId());
        event.setCreatedAt(LocalDateTime.now());
        log.info("Auth {} with saga notified! TransactionId: {}", event.getId(), event.getTransactionId());
    }

    public String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
