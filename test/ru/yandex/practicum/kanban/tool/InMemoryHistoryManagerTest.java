package ru.yandex.practicum.kanban.tool;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.kanban.tool.stuff.Epic;
import ru.yandex.practicum.kanban.tool.stuff.Subtask;
import ru.yandex.practicum.kanban.tool.stuff.Task;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {
    InMemoryHistoryManager inMemoryHistoryManager;

    @BeforeEach
    void beforeEach() {
        inMemoryHistoryManager = new InMemoryHistoryManager();
    }

    @Test
    void add_shouldBeAdded3TypesOfTasks() {
        Task task = new Task();
        task.setId(0);
        Epic epic = new Epic();
        epic.setId(1);
        Subtask subtask = new Subtask();
        subtask.setId(2);
        subtask.setEpic(epic);

        inMemoryHistoryManager.add(task);
        inMemoryHistoryManager.add(epic);
        inMemoryHistoryManager.add(subtask);

        assertEquals(3, inMemoryHistoryManager.getHistory().size(), "Не все 3 типа задач добавлены.");
    }

    @Test
    // Проверка на добавление задач на одну больше, чем длина списока истории. Последняя добавленная задача
    // должна перезаписать первую в списке истории.
    void add_shouldBeAddedOneMoreTaskThanTaskHistoryMaxSize() {
        // Подготовка
        Task task;
        for (int i = 0; i < InMemoryHistoryManager.TASK_HISTORY_MAX_SIZE; i++) {
            task = new Task();
            task.setId(i);
            inMemoryHistoryManager.add(task);
        }

        // Исполнение и проверка
        int id;
        List<Task> tasks = inMemoryHistoryManager.getHistory();
        for (int i = 0; i < tasks.size(); i++) {
            assertEquals(i, tasks.get(i).getId());
        }
        assertEquals(InMemoryHistoryManager.TASK_HISTORY_MAX_SIZE, tasks.size());

        // Подготовка (продолжение). Добавление еще одной задачи
        task = new Task();
        task.setId(InMemoryHistoryManager.TASK_HISTORY_MAX_SIZE);
        inMemoryHistoryManager.add(task);

        // Исполнение и проверка (продолжение)
        tasks = inMemoryHistoryManager.getHistory();
        assertEquals(InMemoryHistoryManager.TASK_HISTORY_MAX_SIZE, tasks.getFirst().getId());
        assertEquals(InMemoryHistoryManager.TASK_HISTORY_MAX_SIZE, tasks.size());
    }
}