package ru.yandex.practicum.kanban.tool;

public class Managers {
    public static TaskManager getDefault() {
        return new InMemoryTaskManager(getDefaultHistory());
    };

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }

}