В моем приложении используются **три основных паттерна проектирования**:
## 1. Singleton
**Используется в:** 
- TaskManager

### Проблема:
TaskManager должен быть единственным источником управления задачами, чтобы избежать рассинхронизации данных.

### Решение:
Singleton гарантирует, что создается только один экземпляр TaskManager на всё приложение. Spring автоматически обеспечивает Singleton, если класс помечен как *@Service*.

## 2. Observer
**Используется в:**
- TaskManager
- TaskObserver интерфейс
- EmailNotificationObserver

Паттерн Наблюдатель позволяет объектам (наблюдателям) подписываться на изменения другого объекта. В моем проекте, когда задача изменяется (например, она добавляется или помечается как выполненная), TaskManager уведомляет всех подписанных наблюдателей (например, EmailNotificationObserver), чтобы те отреагировали на изменение.

Observer упрощает добавление и управление подписчиками. Мы можем динамически добавлять или удалять наблюдателей без изменения логики TaskManager.

## 3. Factory Method (Фабричный метод)

**Используется в:** 
- TaskFactory
- UrgentTaskFactory
- RegularTaskFactory

### Проблема:
Нужно создавать разные типы задач (например, срочные и обычные) без жесткой привязки к конкретным классам.

### Решение:
Factory Method изолирует процесс создания объектов, обеспечивая гибкость и масштабируемость. Если нужно добавить новый тип задачи, достаточно реализовать новую фабрику.
