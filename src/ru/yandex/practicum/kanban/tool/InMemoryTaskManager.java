package ru.yandex.practicum.kanban.tool;

import ru.yandex.practicum.kanban.tool.stuff.Epic;
import ru.yandex.practicum.kanban.tool.stuff.Subtask;
import ru.yandex.practicum.kanban.tool.stuff.Task;

import java.util.HashMap;

public class InMemoryTaskManager implements TaskManager {
    private static int lastTaskId = -1;
    private HashMap<Integer, Task> tasks;
    private HashMap<Integer, Epic> epics;
    private HashMap<Integer, Subtask> subtasks;
    private HistoryManager historyManager;

    public InMemoryTaskManager(HistoryManager historyManager) {
        tasks = new HashMap<>();
        epics = new HashMap<>();
        subtasks = new HashMap<>();
        this.historyManager = historyManager;
    }

    @Override
    public HashMap<Integer, Task> getTasks() {
        return tasks;
    }

    @Override
    public HashMap<Integer, Epic> getEpics() {
        return epics;
    }

    @Override
    public HashMap<Integer, Subtask> getSubtasks() {
        return subtasks;
    }

    public HistoryManager getHistoryManager() {
        return historyManager;
    }

    @Override
    public void clearTasks() {
        tasks.clear();
    }

    @Override
    public void clearEpics() {
        subtasks.clear();
        epics.clear();
    }

    @Override
    public void clearSubtasks() {
        subtasks.clear();
        for (Epic epic : epics.values()) {
            epic.getSubtasks().clear();
        }
    }

    @Override
    public Task getTask(Integer id) {
        if (tasks.containsKey(id)) {
            historyManager.add(tasks.get(id));
        }
        return tasks.get(id);
    }

    @Override
    public Epic getEpic(Integer id) {
        if (epics.containsKey(id)) {
            historyManager.add(epics.get(id));
        }
        return epics.get(id);
    }

    @Override
    public Subtask getSubtask(Integer id) {
        if (subtasks.containsKey(id)) {
            historyManager.add(subtasks.get(id));
        }
        return subtasks.get(id);
    }

    @Override
    public Task createTask(Task task) {
        if (task == null) {
            return null;
        }
        task.setId(++lastTaskId);
        tasks.put(lastTaskId, task);
        return task;
    }

    // Эпик создается пустой, без подзадач
    @Override
    public Epic createEpic(Epic epic) {
        if (epic == null) {
            return null;
        }
        epic.setId(++lastTaskId);
        epic.calculateStatus();
        epics.put(lastTaskId, epic);
        return epic;
    }

    // Подзадачи создаются с привязкой к эпику
    @Override
    public Subtask createSubtask(Subtask subtask, Epic epic) {
        if (subtask == null || epic == null || !epic.isIdAssigned()) {
            return null;
        }
        subtask.setId(++lastTaskId);
        subtask.setEpic(epic);
        subtask.getEpic().calculateStatus();
        subtasks.put(lastTaskId, subtask);
        return subtask;
    }

    @Override
    public boolean updateTask(Task task) {
        if (task == null || !tasks.containsKey(task.getId())) {
            return false;
        } else {
            tasks.put(task.getId(), task);
            return true;
        }
    }

    // При обновлении эпика связи с подзадачами не изменяются, поэтому пересчитывать статус эпика тоже не надо.
    @Override
    public boolean updateEpic(Epic epic) {
        if (epic == null || !epics.containsKey(epic.getId())) {
            return false;
        } else {
            Epic epicForUpdate = epics.get(epic.getId());
            epicForUpdate.setName(epic.getName());
            epicForUpdate.setDescription(epic.getDescription());
            return true;
        }
    }

    /*
        При обновлении подзадачи связь с эпиком не изменяется, но может измениться статус подзадачи, поэтому
        статус эпика надо пересчитать.
    */
    @Override
    public boolean updateSubtask(Subtask subtask) {
        if (subtask == null || !subtasks.containsKey(subtask.getId())) {
            return false;
        } else {
            Subtask subtaskForUpdate = subtasks.get(subtask.getId());
            subtaskForUpdate.setName(subtask.getName());
            subtaskForUpdate.setDescription(subtask.getDescription());
            subtaskForUpdate.setStatus(subtask.getStatus());
            subtaskForUpdate.getEpic().calculateStatus();
            return true;
        }
    }

    // Возвращает удаленную задачу
    @Override
    public Task removeTask(Integer id) {
        if (id == null || !tasks.containsKey(id)) {
            return null;
        }
        return tasks.remove(id);
    }

    // Возвращает удаленный эпик
    @Override
    public Task removeEpic(Integer id) {
        if (id == null || !epics.containsKey(id)) {
            return null;
        }
        for (Subtask subtask : epics.get(id).getSubtasks().values()) {
            subtasks.remove(subtask.getId());
        }
        return epics.remove(id);
    }

    // Возвращает удаленную подзадачу
    @Override
    public Task removeSubtask(Integer id) {
        if (id == null || !subtasks.containsKey(id)) {
            return null;
        }
        Subtask removedSubtask = subtasks.remove(id);
        removedSubtask.getEpic().getSubtasks().remove(id);
        removedSubtask.getEpic().calculateStatus();
        return removedSubtask;
    }

    @Override
    public HashMap<Integer, Subtask> getSubtasks(Epic epic) {
        if (epic == null) {
            return null;
        }
        return epic.getSubtasks();
    }
}