package org.example.taskmanagement.model;

// Абстрактный класс Task
public abstract class Task {
    private final String description;

    public Task(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Task{" + "description='" + description + '\'' + '}';
    }
}