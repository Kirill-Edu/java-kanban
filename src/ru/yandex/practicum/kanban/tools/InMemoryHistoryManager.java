package ru.yandex.practicum.kanban.tools;

import ru.yandex.practicum.kanban.tools.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    private List<Task> taskHistory;
    private int nextTaskHistoryIndex;
    public static final int TASK_HISTORY_MAX_SIZE = 10;

    public InMemoryHistoryManager() {
        taskHistory = new ArrayList<>();
        nextTaskHistoryIndex = 0;
    }

    @Override
    public boolean add(Task task) {
        boolean result;
        if (taskHistory.size() < TASK_HISTORY_MAX_SIZE) {
            result = taskHistory.add(task);
        } else {
            taskHistory.set(nextTaskHistoryIndex++, task);
            if (nextTaskHistoryIndex == TASK_HISTORY_MAX_SIZE) {
                nextTaskHistoryIndex = 0;
            }
            result = true;
        }
        return result;
    }

    @Override
    public List<Task> getHistory() {
        return new ArrayList<>(taskHistory);
    }
}
