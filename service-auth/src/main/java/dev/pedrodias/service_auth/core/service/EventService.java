package dev.pedrodias.service_auth.core.service;

import dev.pedrodias.service_auth.core.document.Event;
import dev.pedrodias.service_auth.core.repositories.EventRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@AllArgsConstructor
public class EventService {

    private final EventRepository repository;

    public void notifyEnding(Event event) {
        event.setId(event.getPayload().getId());
        event.setCreatedAt(LocalDateTime.now());
        save(event);
        log.info("User {} with saga notified! TransactionId: {}", event.getId(), event.getTransactionId());
    }

    public Event save(Event event) {
        return repository.save(event);
    }
}
