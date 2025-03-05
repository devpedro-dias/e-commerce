package dev.pedrodias.serviceauth.core.repositories;

import dev.pedrodias.serviceauth.core.document.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<Event, String> {
}
