package org.example.taskmanagement.config;

import org.example.taskmanagement.factory.RegularTaskFactory;
import org.example.taskmanagement.factory.UrgentTaskFactory;
import org.example.taskmanagement.observer.EmailNotificationObserver;
import org.example.taskmanagement.service.TaskManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Конфигурация
@Configuration
public class AppConfig {
    @Bean
    public TaskManager taskManager() {
        return new TaskManager();
    }

    @Bean
    public UrgentTaskFactory urgentTaskFactory() {
        return new UrgentTaskFactory();
    }

    @Bean
    public RegularTaskFactory regularTaskFactory() {
        return new RegularTaskFactory();
    }

    @Bean
    public EmailNotificationObserver emailNotificationObserver() {
        return new EmailNotificationObserver();
    }
}