package com.amanda.manager.tasks.specifications;

import com.amanda.manager.tasks.entity.Task;
import com.amanda.manager.tasks.enums.TaskPriority;
import com.amanda.manager.tasks.enums.TaskStatus;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class TaskSpecification {

    public static Specification<Task> hasStatus(TaskStatus status) {
        return ((root, query, cb) -> status == null ? null : cb.equal(root.get("status"), status));
    }

    public static Specification<Task> hasPriority(TaskPriority priority) {
        return (root, query, cb) -> priority == null ? null : cb.equal(root.get("priority"), priority);
    }

    public static Specification<Task> hasUser(String user)
    {
        return ((root, query, cb) -> (user == null  || user.isBlank() ) ? null : cb.equal(root.get("user"), user));
    }

    public static Specification<Task> deadlineAfter(LocalDateTime start)
    {
        return (root, query, cb) ->  start == null ? null : cb.equal(root.get("deadlineDate"), start);
    }

    public static Specification<Task> deadlineBefore(LocalDateTime end)
    {
        return (root, query, cb) ->  end == null ? null : cb.equal(root.get("deadlineDate"), end);
    }
}
