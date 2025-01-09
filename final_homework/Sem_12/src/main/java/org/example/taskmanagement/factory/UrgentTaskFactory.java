package org.example.taskmanagement.factory;

import org.example.taskmanagement.model.Task;
import org.example.taskmanagement.model.UrgentTask;
import org.springframework.stereotype.Component;

// Factory Method
// Реализация фабрики - Срочная
@Component
public class UrgentTaskFactory implements TaskFactory {
    @Override
    public Task createTask(String description) {
        return new UrgentTask(description);
    }
}