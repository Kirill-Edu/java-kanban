package ru.yandex.practicum.kanban;

/*
    Пояснения к реализации технического задания проекта №4:
    1. В условии технического задания написано: "При создании задачи менеджер присваивает
        ей новый идентификатор.". Связь между эпиком и подзадачей можно установить только после того, как они
        получат индентификаторы, поэтому в конструкторе подзадачи установить связь между ней и эпиком не получится.
        Таким образом связь устанавливается при вызове метода createSubtask класса TaskManager.
    2. В условии технического задания не было рекомендаций, какую струкруту данных использовать для хранения в эпике
        подзадач, поэтому мной была выбрана HashMap. Позже в чате java-dev_theory узнал, что достаточно было
        использовать ArrayList, но переделывать уже не стал.

    Порядок работы:
    1. Для создания эпиков и подзадач методы класса TaskManager надо вызывать в такой последовательности:
        а) сначала создать эпик - createEpic(Epic epic);
        б) потом создать подзадачу с привязкой к созданному эпику - createSubtask(Subtask subtask, Epic epic).

    Принципы работы:
    1. При обновлении эпика или подзадачи связи между ними не изменяются.
    2. Идентификатор устанавливается задаче через метод setId() и только один раз.
 */

import ru.yandex.practicum.kanban.tool.stuff.*;
import ru.yandex.practicum.kanban.tool.*;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = Managers.getDefault();

        /*
            Спринт 4.
         */

        /*
            Создайте две задачи, а также эпик с двумя подзадачами и эпик с одной подзадачей.
            Распечатайте списки эпиков, задач и подзадач.
        */
        Task taskOne = taskManager.createTask(new Task("Задача №1", "Описание задачи №1."));
        Task taskTwo = taskManager.createTask(new Task("Задача №2", "Описание задачи №2."));

        Epic epicWithOneSubtask = taskManager.createEpic(new Epic("Эпик с одной подзадачей"
                , "Описание эпика с одной подзадачей"));
        Subtask subtaskOfEpicWithOneSubtask = taskManager.createSubtask(new Subtask(
                "Подзадача в эпике с одной подзадачей"
                , "Описание подзадачи в эпике с одной подзадачей"), epicWithOneSubtask);

        Epic epicWithTwoSubtasks = taskManager.createEpic(new Epic("Эпик с двумя подзадачами"
                , "Описание эпика с двумя подзадачами"));
        Subtask subtaskOneOfEpicWithTwoSubtasks = taskManager.createSubtask(
                new Subtask("Подзадача №1 в эпике с двумя подзадачами"
                , "Описание подзадачи №1 в эпике с двумя подзадачами"), epicWithTwoSubtasks);
        Subtask subtaskTwoOfEpicWithTwoSubtasks = taskManager.createSubtask(
                new Subtask("Подзадача №2 в эпике с двумя подзадачами"
                        , "Описание подзадачи №2 в эпике с двумя подзадачами"), epicWithTwoSubtasks);

        System.out.println("Список задач:");
        System.out.println(taskOne);
        System.out.println(taskTwo);

        System.out.println("\nСписок эпиков:");
        System.out.println(epicWithOneSubtask);
        System.out.println(epicWithTwoSubtasks);

        System.out.println("\nСписок подзадач:");
        System.out.println(subtaskOfEpicWithOneSubtask);
        System.out.println(subtaskOneOfEpicWithTwoSubtasks);
        System.out.println(subtaskTwoOfEpicWithTwoSubtasks);

        /*
            Измените статусы созданных объектов, распечатайте их. Проверьте, что статус задачи и подзадачи сохранился,
            а статус эпика рассчитался по статусам подзадач.
        */
        System.out.println("\nИзменение статусов для задач...");
        System.out.println("Для первой - IN_PROGRESS, для второй - DONE.");
        taskOne.setStatus(TaskStatus.IN_PROGRESS);
        taskTwo.setStatus(TaskStatus.DONE);
        taskManager.updateTask(taskOne);
        taskManager.updateTask(taskTwo);
        System.out.println("Статусы для задач изменены:");
        System.out.println(taskOne);
        System.out.println(taskTwo);

        // Первая смена статусов для подзадач
        System.out.println("\nИзменение статусов для подзадач...");
        System.out.println("Для подзадачи в эпике с одной подзадачей - IN_PROGRESS.");
        System.out.println("Для подзадачи №1 в эпике с двумя задачами - IN_PROGRESS.");
        System.out.println("Для подзадачи №2 в эпике с двумя задачами - DONE.");
        subtaskOfEpicWithOneSubtask.setStatus(TaskStatus.IN_PROGRESS);
        subtaskOneOfEpicWithTwoSubtasks.setStatus(TaskStatus.IN_PROGRESS);
        subtaskTwoOfEpicWithTwoSubtasks.setStatus(TaskStatus.DONE);
        taskManager.updateSubtask(subtaskOfEpicWithOneSubtask);
        taskManager.updateSubtask(subtaskOneOfEpicWithTwoSubtasks);
        taskManager.updateSubtask(subtaskTwoOfEpicWithTwoSubtasks);
        System.out.println("Статусы для подзадач изменены.");
        System.out.println("Список подзадач:");
        System.out.println(subtaskOfEpicWithOneSubtask);
        System.out.println(subtaskOneOfEpicWithTwoSubtasks);
        System.out.println(subtaskTwoOfEpicWithTwoSubtasks);
        System.out.println("Список эпиков с пересчитанными статусами:");
        System.out.println(epicWithOneSubtask);
        System.out.println(epicWithTwoSubtasks);

        // Вторая смена статусов для подзадач
        System.out.println("\nИзменение статусов для подзадач...");
        System.out.println("Для подзадачи в эпике с одной подзадачей - DONE.");
        System.out.println("Для подзадачи №1 в эпике с двумя задачами - DONE.");
        System.out.println("Для подзадачи №2 в эпике с двумя задачами - DONE.");
        subtaskOfEpicWithOneSubtask.setStatus(TaskStatus.DONE);
        subtaskOneOfEpicWithTwoSubtasks.setStatus(TaskStatus.DONE);
        subtaskTwoOfEpicWithTwoSubtasks.setStatus(TaskStatus.DONE);
        taskManager.updateSubtask(subtaskOfEpicWithOneSubtask);
        taskManager.updateSubtask(subtaskOneOfEpicWithTwoSubtasks);
        taskManager.updateSubtask(subtaskTwoOfEpicWithTwoSubtasks);
        System.out.println("Статусы для подзадач изменены.");
        System.out.println("Список подзадач:");
        System.out.println(subtaskOfEpicWithOneSubtask);
        System.out.println(subtaskOneOfEpicWithTwoSubtasks);
        System.out.println(subtaskTwoOfEpicWithTwoSubtasks);
        System.out.println("Список эпиков с пересчитанными статусами:");
        System.out.println(epicWithOneSubtask);
        System.out.println(epicWithTwoSubtasks);

        /*
            И, наконец, попробуйте удалить одну из задач и один из эпиков.
         */
        System.out.println("\nУдаление задачи и эпика с двумя подзадачами...");
        taskManager.removeTask(taskTwo.getId());
        taskManager.removeEpic(epicWithTwoSubtasks.getId());
        System.out.println("Удаление выполнено.");
        System.out.println("Список оставшихся задач:");
        System.out.println(taskManager.getTasks());
        System.out.println("Список оставшихся подзадач:");
        System.out.println(taskManager.getSubtasks());
        System.out.println("Список оставшихся эпиков:");
        System.out.println(taskManager.getEpics());


        /*
            Спринт 5
         */
        System.out.println("\nСписок просмотров:");
        for (int i = 0; i < 9; i++) {
            taskManager.getTask(0);
        }
        for (int i = 0; i < 2; i++) {
            taskManager.getEpic(2);
        }
        for (int i = 0; i < 2; i++) {
            taskManager.getSubtask(3);
        }
        System.out.println(taskManager.getHistoryManager().getHistory());
    }
}
