package ru.yandex.practicum.kanban.tool;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.kanban.tool.stuff.Epic;
import ru.yandex.practicum.kanban.tool.stuff.Subtask;
import ru.yandex.practicum.kanban.tool.stuff.Task;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {

    InMemoryTaskManager inMemoryTaskManager;
    Task task;
    Epic epic;
    Subtask subtask;
    @BeforeEach
    void beforeEach() {
        inMemoryTaskManager = new InMemoryTaskManager(new InMemoryHistoryManager());
        task = inMemoryTaskManager.createTask(new Task());
        epic = inMemoryTaskManager.createEpic(new Epic());
        subtask = inMemoryTaskManager.createSubtask(new Subtask(), epic);
    }

    @Test
    void getTask_shouldNotBeNull() {
        assertNotNull(inMemoryTaskManager.getTask(task.getId()));
    }

    @Test
    void getEpic_shouldNotBeNull() {
        assertNotNull(inMemoryTaskManager.getEpic(epic.getId()));
    }

    @Test
    void getSubtask_shouldNotBeNull() {
        assertNotNull(inMemoryTaskManager.getSubtask(subtask.getId()));
    }

    @Test
    void createTask_shouldBeOneTask() {
        assertEquals(1, inMemoryTaskManager.getTasks().size());
    }

    @Test
    void createEpic_shouldBeOneEpic() {
        assertEquals(1, inMemoryTaskManager.getEpics().size());
    }

    @Test
    void createSubtask_shouldBeOneSubtask() {
        assertEquals(1, inMemoryTaskManager.getSubtasks().size());
    }
}