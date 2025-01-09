package org.example.taskmanagement.factory;

import org.example.taskmanagement.model.Task;

public interface TaskFactory {
    // Метод для создания задачи
    Task createTask(String description);
}
