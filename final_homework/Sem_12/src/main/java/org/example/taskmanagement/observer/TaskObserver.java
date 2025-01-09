package org.example.taskmanagement.observer;

import org.example.taskmanagement.model.Task;

// Интерфейс для наблюдателей
public interface TaskObserver {
    void update(Task task);
}