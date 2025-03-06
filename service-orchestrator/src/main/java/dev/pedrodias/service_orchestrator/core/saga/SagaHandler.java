package dev.pedrodias.service_orchestrator.core.saga;

import static dev.pedrodias.service_orchestrator.core.enums.ETopics.*;
import static dev.pedrodias.service_orchestrator.core.enums.EEventSource.*;
import static dev.pedrodias.service_orchestrator.core.enums.ESagaStatus.*;

public final class SagaHandler {

    private SagaHandler() {

    }

    public static final Object[][] SAGA_HANDLER = {
            { SERVICE_AUTH, SUCCESS, START_SAGA },
            { SERVICE_AUTH, ROLLBACK_PENDING, USER_CREATED_FAIL },
            { SERVICE_AUTH, FAIL, FINISH_FAIL },

            { SERVICE_USER, SUCCESS, USER_CREATED_SUCCESS },
            { SERVICE_USER, FAIL, USER_CREATED_FAIL },
            { SERVICE_USER, ROLLBACK_PENDING, USER_CREATED_FAIL },
    };

    public static final int EVENT_SOURCE_INDEX = 0;
    public static final int SAGA_STATUS_INDEX = 1;
    public static final int TOPIC_INDEX = 2;
}
