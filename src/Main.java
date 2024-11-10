public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        // Создаем обычные задачи
        Task task1 = new Task("Уборка", "Убраться в квартире");
        Task task2 = new Task("Покупки", "Сходить в магазин за продуктами");
        manager.createTask(task1);
        manager.createTask(task2);

        // Создаем первый эпик с двумя подзадачами
        Epic epic1 = new Epic("Организация вечеринки", "Подготовка к вечеринке на выходных");
        manager.createEpic(epic1);

        Subtask subtask1 = new Subtask("Купить торт", "Заказать торт в кондитерской", epic1.getId());
        Subtask subtask2 = new Subtask("Пригласить друзей", "Создать чат и пригласить друзей", epic1.getId());
        manager.createSubtask(subtask1);
        manager.createSubtask(subtask2);

        // Создаем второй эпик с одной подзадачей
        Epic epic2 = new Epic("Переезд", "Подготовка к переезду в новую квартиру");
        manager.createEpic(epic2);

        Subtask subtask3 = new Subtask("Упаковка вещей", "Упаковать все в коробки", epic2.getId());
        manager.createSubtask(subtask3);

        // Печать всех задач
        System.out.println("Все задачи:");
        for (Task task : manager.getAllTasks()) {
            System.out.println(" - " + task.getName() + ": " + task.getDescription() + " [Статус: " + task.getStatus() + "]");
        }

        // Печать подзадач для каждого эпика
        System.out.println("\nПодзадачи для эпика 'Организация вечеринки':");
        for (Subtask subtask : manager.getSubtasksForEpic(epic1.getId())) {
            System.out.println(" - " + subtask.getName() + ": " + subtask.getDescription() + " [Статус: " + subtask.getStatus() + "]");
        }

        System.out.println("\nПодзадачи для эпика 'Переезд':");
        for (Subtask subtask : manager.getSubtasksForEpic(epic2.getId())) {
            System.out.println(" - " + subtask.getName() + ": " + subtask.getDescription() + " [Статус: " + subtask.getStatus() + "]");
        }

        // Изменение статусов задач и подзадач
        task1.setStatus(TaskStatus.DONE);
        manager.updateTask(task1);

        subtask1.setStatus(TaskStatus.DONE);
        manager.updateSubtask(subtask1);

        subtask2.setStatus(TaskStatus.IN_PROGRESS);
        manager.updateSubtask(subtask2);

        // Проверка статусов после изменений
        System.out.println("\nСтатусы после изменений:");
        System.out.println("Задача 'Уборка' - [Статус: " + manager.getTaskById(task1.getId()).getStatus() + "]");
        System.out.println("Подзадача 'Купить торт' - [Статус: " + manager.getSubtaskById(subtask1.getId()).getStatus() + "]");
        System.out.println("Эпик 'Организация вечеринки' - [Статус: " + manager.getEpicById(epic1.getId()).getStatus() + "]");

        // Удаление подзадачи по ID и пересчет статуса эпика
        manager.deleteSubtaskById(subtask2.getId());
        System.out.println("\nПосле удаления подзадачи 'Пригласить друзей':");
        System.out.println("Эпик 'Организация вечеринки' - [Статус: " + manager.getEpicById(epic1.getId()).getStatus() + "]");

        // Удаление задачи и эпика
        manager.deleteTaskById(task2.getId());
        manager.deleteEpicById(epic2.getId());

        // Печать задач после удаления
        System.out.println("\nСписок задач после удаления:");
        for (Task task : manager.getAllTasks()) {
            System.out.println(" - " + task.getName() + ": " + task.getDescription() + " [Статус: " + task.getStatus() + "]");
        }

        // Удаление всех задач и подзадач
        manager.deleteAllTasksAndSubtasks();
        System.out.println("\nПосле удаления всех задач и подзадач:");
        if (manager.getAllTasks().isEmpty()) {
            System.out.println("Нет задач.");
        }
    }
}

