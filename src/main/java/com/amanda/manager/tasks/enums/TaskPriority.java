package com.amanda.manager.tasks.enums;

import io.swagger.v3.oas.annotations.media.Schema;

public enum TaskPriority {

    @Schema(description = "Low priority task")
    LOW,
    @Schema(description = "Normal priority task")
    MEDIUM,
    @Schema(description = "High Urgency task")
    HIGH
}
