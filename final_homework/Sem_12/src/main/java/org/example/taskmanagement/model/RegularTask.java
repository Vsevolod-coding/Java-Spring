package org.example.taskmanagement.model;

// Тип задачи - Обычная
public class RegularTask extends Task {
    public RegularTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "Regular " + super.toString();
    }
}
