package org.example.taskmanagement.service;

import org.example.taskmanagement.observer.TaskObserver;
import org.example.taskmanagement.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// Singleton
@Service
public class TaskManager {
    private final List<Task> tasks = new ArrayList<>();
    private final List<TaskObserver> observers = new ArrayList<>();

    // Добавление задачи и уведомление наблюдателей
    public void addTask(Task task) {
        tasks.add(task);
        notifyObservers(task);
    }

    // Просмотр всех задач
    public List<Task> getAllTasks() {
        return tasks;
    }

    // Отметка задачи как 'Завершенная'
    public void markTaskAsDone(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task task = tasks.get(taskIndex);
            System.out.println("Task completed: " + task);
            notifyObservers(task);
            tasks.remove(taskIndex);
        } else {
            System.out.println("Invalid task index!");
        }
    }

    // Добавление наблюдателя
    public void addObserver(TaskObserver observer) {
        observers.add(observer);
    }

    // Уведомление наблюдателей об изменениях
    private void notifyObservers(Task task) {
        for (TaskObserver observer : observers) {
            observer.update(task);
        }
    }
}