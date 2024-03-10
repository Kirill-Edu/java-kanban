package ru.yandex.practicum.kanban.tool;

import ru.yandex.practicum.kanban.tool.stuff.Epic;
import ru.yandex.practicum.kanban.tool.stuff.Subtask;
import ru.yandex.practicum.kanban.tool.stuff.Task;

import java.util.HashMap;

public interface TaskManager {
    HashMap<Integer, Task> getTasks();

    HashMap<Integer, Epic> getEpics();

    HashMap<Integer, Subtask> getSubtasks();

    HistoryManager getHistoryManager();

    void clearTasks();

    void clearEpics();

    void clearSubtasks();

    Task getTask(Integer id);

    Epic getEpic(Integer id);

    Subtask getSubtask(Integer id);

    Task createTask(Task task);

    // Эпик создается пустой, без подзадач
    Epic createEpic(Epic epic);

    // Подзадачи создаются с привязкой к эпику
    Subtask createSubtask(Subtask subtask, Epic epic);

    boolean updateTask(Task task);

    // При обновлении эпика связи с подзадачами не изменяются, поэтому пересчитывать статус эпика тоже не надо.
    boolean updateEpic(Epic epic);

    /*
        При обновлении подзадачи связь с эпиком не изменяется, но может измениться статус подзадачи, поэтому
        статус эпика надо пересчитать.
    */
    boolean updateSubtask(Subtask subtask);

    // Возвращает удаленную задачу
    Task removeTask(Integer id);

    // Возвращает удаленный эпик
    Task removeEpic(Integer id);

    // Возвращает удаленную подзадачу
    Task removeSubtask(Integer id);

    HashMap<Integer, Subtask> getSubtasks(Epic epic);
}
