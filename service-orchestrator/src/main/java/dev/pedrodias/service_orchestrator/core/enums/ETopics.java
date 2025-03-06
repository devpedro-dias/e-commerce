package dev.pedrodias.service_orchestrator.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ETopics {

    START_SAGA("start-saga"),
    BASE_ORCHESTRATOR("orchestrator"),
    USER_CREATED("user-created"),
    USER_CREATED_SUCCESS("user-created-success"),
    USER_CREATED_FAIL("user-created-fail"),
    FINISH_SUCCESS("finish-success"),
    FINISH_FAIL("finish-fail"),
    NOTIFY_ENDING("notify-ending");

    private final String topic;
}
