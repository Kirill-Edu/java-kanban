package ru.yandex.practicum.kanban.tools.tasks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubtaskTest {
    Subtask subtaskOne;
    Subtask subtaskTwo;

    @BeforeEach
    void beforeEach() {
        subtaskOne = new Subtask();
        subtaskTwo = new Subtask();
    }

    @Test
    void equals_shouldBeEqualsWithTheSameId() {
        subtaskOne.setId(0);
        subtaskTwo.setId(0);

        assertEquals(subtaskOne, subtaskTwo, "Id подзадач не совпадают");
    }
}