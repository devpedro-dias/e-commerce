package dev.pedrodias.service_auth.core.repositories;

import dev.pedrodias.service_auth.core.document.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<Event, String> {

}
