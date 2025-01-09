package org.example.taskmanagement.factory;

import org.example.taskmanagement.model.RegularTask;
import org.example.taskmanagement.model.Task;
import org.springframework.stereotype.Component;

// Factory Method
// Реализация фабрики - Обычная
@Component
public class RegularTaskFactory implements TaskFactory {
    @Override
    public Task createTask(String description) {
        return new RegularTask(description);
    }
}

