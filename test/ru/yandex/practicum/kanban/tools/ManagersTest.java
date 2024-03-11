package ru.yandex.practicum.kanban.tools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagersTest {

    @Test
    void getDefault_shouldNotBeNull() {
        TaskManager taskManager = Managers.getDefault();

        assertNotNull(taskManager);
    }

    @Test
    void getDefaultHistory_shouldNotBeNull() {
        HistoryManager historyManager = Managers.getDefaultHistory();

        assertNotNull(historyManager);
    }
}