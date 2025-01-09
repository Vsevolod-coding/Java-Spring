package org.example.taskmanagement.model;

// Тип задачи - Срочная
public class UrgentTask extends Task {
    public UrgentTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "Urgent " + super.toString();
    }
}