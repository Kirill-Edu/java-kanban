package ru.yandex.practicum.kanban.stuff;

public class Subtask extends Task {
    private Epic epic;

    public Subtask() {
        this.epic = new Epic();
    }

    public Subtask(String name, String description) {
        this();
        setName(name);
        setDescription(description);
    }

    public void setEpic(Epic epic) {
        if (isIdAssigned() || epic.isIdAssigned()) {
            // Сначала отвязать подзадачу от текущего эпика
            if (this.epic.hasSubtasks()) {
                this.epic.getSubtasks().remove(getId());
            }
            // Теперь привязать подзадачу к новому эпику
            this.epic = epic;
            this.epic.getSubtasks().put(getId(), this);
        }
    }

    public Epic getEpic() {
        return epic;
    }

    @Override
    public String toString() {
        String result = super.toString().replace("Task", "Subtask");
        result = result.substring(0, result.length() - 1)
                + ", epic=Epic{"
                + "id='" + epic.getId() + '\''
                + ", idAssigned='" + epic.isIdAssigned() + '\''
                + ", name='" + epic.getName() + '\'';
        if (epic.getDescription() == null) {
            result += ", description.length=null";
        } else {
            result += ", description.length='" + epic.getDescription().length() + '\'';
        }
        result += ", status='" + epic.getStatus() + "'}}";
        return result;
    }
}
