package com.amanda.manager.tasks.enums;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

public enum TaskStatus {
    @Schema(description = "Task has not started yet")
    PENDING,
    @Schema(description = "Task is in progress")
    IN_PROGRESS,
    @Schema(description = "Task is completed")
    DONE;
}


