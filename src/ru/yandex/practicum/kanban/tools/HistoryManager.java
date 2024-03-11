package ru.yandex.practicum.kanban.tools;

import ru.yandex.practicum.kanban.tools.tasks.Task;

import java.util.List;

public interface HistoryManager {

    boolean add(Task task);

    List<Task> getHistory();
}
