package org.example.taskmanagement;

import org.example.taskmanagement.config.AppConfig;
import org.example.taskmanagement.factory.RegularTaskFactory;
import org.example.taskmanagement.factory.TaskFactory;
import org.example.taskmanagement.factory.UrgentTaskFactory;
import org.example.taskmanagement.model.Task;
import org.example.taskmanagement.observer.EmailNotificationObserver;
import org.example.taskmanagement.service.TaskManager;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Запуск приложения
        startApp();
    }

    // Метод для запуска приложения и управления пользовательским меню
    private static void startApp() {
        // Инициализация контекста Spring с использованием AppConfig
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Получение необходимых бинов из контекста
        TaskManager taskManager = context.getBean(TaskManager.class);
        TaskFactory urgentFactory = context.getBean(UrgentTaskFactory.class);
        TaskFactory regularFactory = context.getBean(RegularTaskFactory.class);

        // Добавление наблюдателя, который будет отслеживать изменения задач
        taskManager.addObserver(context.getBean(EmailNotificationObserver.class));

        // Пользовательское меню
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Task Management Menu ===");
            System.out.println("1. Create a Task");
            System.out.println("2. View All Tasks");
            System.out.println("3. Mark a Task as Done");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            // Считывание ввода пользователя с проверкой на корректность
            int choice = readInt(scanner);
            if (choice == -1) continue; // Некорректный ввод - возвращаемся к меню

            // Обработка выбора пользователя
            switch (choice) {
                case 1:
                    createTask(taskManager, urgentFactory, regularFactory, scanner);
                    break;
                case 2:
                    viewTasks(taskManager);
                    break;
                case 3:
                    markTaskAsDone(taskManager, scanner);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    context.close(); // Закрытие контекста Spring
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Метод для создания задачи с выбором типа задачи и ввода описания
    private static void createTask(TaskManager taskManager, TaskFactory urgentFactory,
                                   TaskFactory regularFactory, Scanner scanner) {
        System.out.println("\nChoose Task Type:");
        System.out.println("1. Urgent Task");
        System.out.println("2. Regular Task");
        System.out.print("Enter choice: ");

        int typeChoice = readInt(scanner);
        if (typeChoice == -1) return; // Некорректный ввод - возвращаемся к меню

        System.out.println("Enter task description: ");
        String description = new Scanner(System.in).nextLine();

        Task task;
        if (typeChoice == 1) {
            task = urgentFactory.createTask(description);
        } else if (typeChoice == 2) {
            task = regularFactory.createTask(description);
        } else {
            System.out.println("Invalid task type!");
            return;
        }

        // Добавление задачи в менеджер задач и вывод подтверждения
        taskManager.addTask(task);
        System.out.println("Task created: " + task);
    }

    // Просмотр всех задач
    private static void viewTasks(TaskManager taskManager) {
        if (taskManager.getAllTasks().isEmpty()) {
            System.out.println("\nNo tasks available.");
        } else {
            System.out.println("\n=== All Tasks ===");
            int index = 1;
            for (Task task : taskManager.getAllTasks()) {
                System.out.println(index++ + ". " + task);
            }
        }
    }

    // Отметка задачи как 'Выполнена'
    private static void markTaskAsDone(TaskManager taskManager, Scanner scanner) {
        viewTasks(taskManager);
        if (taskManager.getAllTasks().isEmpty()) {
            return;
        }
        System.out.print("\nEnter the number of the task to mark as done: ");

        int taskIndex = readInt(scanner) - 1; // Преобразуем в 0-based индекс
        if (taskIndex == -2) return; // Некорректный ввод - возвращаемся к меню

        taskManager.markTaskAsDone(taskIndex);
    }

    // Вспомогательный метод для обработки некорректного ввода
    private static int readInt(Scanner scanner) {
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Очищаем буфер ввода
            return -1; // Спец. значение для обработки ошибки
        }
    }
}