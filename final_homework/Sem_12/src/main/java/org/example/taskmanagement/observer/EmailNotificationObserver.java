package org.example.taskmanagement.observer;

import org.example.taskmanagement.model.Task;
import org.springframework.stereotype.Component;

// Observer
@Component
public class EmailNotificationObserver implements TaskObserver {
    // Реализация уведомления по email при изменении состояния задачи
    @Override
    public void update(Task task) {
        System.out.println("Email Notification: Task updated - " + task);
    }
}
