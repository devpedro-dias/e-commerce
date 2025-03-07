package dev.pedrodias.service_auth.core.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.pedrodias.service_auth.core.document.Event;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class JsonUtil {

    private final ObjectMapper objectMapper;

    public String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            return "";
        }
    }

    public Event toEvent(String json) {
        try {
            return objectMapper.readValue(json, Event.class);
        } catch (Exception e) {
            return null;
        }
    }
}
