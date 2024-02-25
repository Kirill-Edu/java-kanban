package ru.yandex.practicum.kanban.stuff;

import java.util.Objects;

public class Task {
    protected static int defaultId = -1; // Идентификатор по умолчанию, когда он еще не присвоен задаче
    protected int id;
    protected boolean idAssigned; // Признак присвоения индетификатора менеджером задач
    protected String name;
    protected String description;
    protected TaskStatus status;

    public Task() {
        id = defaultId;
        idAssigned = false;
        status = TaskStatus.NEW;
    }
    public Task(String name, String description) {
        this();
        this.name = name;
        this.description = description;
    }
    public int getId() {
        return id;
    }

    // Идентификатор задачи устанавливается только один раз!
    public void setId(int id) {
        if (id > defaultId && !idAssigned) {
            this.id = id;
            idAssigned = true;
        }
    }

    public boolean isIdAssigned() {
        return idAssigned;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        String result = "Task{"
            + "id='" + id + '\''
            + ", idAssigned='" + idAssigned + '\''
            + ", name='" + name + '\'';
        if (description == null) {
            result += ", description.length=null";
        } else {
            result += ", description.length='" + description.length() + '\'';
        }
        result += ", status='" + status + "'}";
        return result;
    }
}
