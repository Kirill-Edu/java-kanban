package ru.yandex.practicum.kanban.tool;

import ru.yandex.practicum.kanban.tool.stuff.Task;

import java.util.List;

public interface HistoryManager {

    boolean add(Task task);

    List<Task> getHistory();
}
