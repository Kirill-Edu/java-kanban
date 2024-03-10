package ru.yandex.practicum.kanban.tool.stuff;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {

    Epic epicOne;
    Epic epicTwo;

    @BeforeEach
    void beforeEach() {
        epicOne = new Epic();
        epicTwo = new Epic();
    }

    @Test
    void equals_shouldBeEqualsWithTheSameId() {
        epicOne.setId(0);
        epicTwo.setId(0);

        assertEquals(epicOne, epicTwo, "Id эпиков не совпадают");
    }
}