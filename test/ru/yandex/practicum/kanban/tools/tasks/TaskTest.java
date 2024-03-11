package ru.yandex.practicum.kanban.tools.tasks;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskTest {

    Task taskOne;
    Task taskTwo;
    @BeforeEach
    void beforeEach() {
        taskOne = new Task();
        taskTwo = new Task();
    }

    @Test
    void equals_shouldBeEqualsWithTheSameId() {
        taskOne.setId(0);
        taskTwo.setId(0);

        assertEquals(taskOne, taskTwo, "Id задач не совпадают");
    }
}