import java.util.HashMap;

public class Epic extends Task {
    private HashMap<Integer, Subtask> subtasks;

    public Epic() {
        subtasks = new HashMap<>();
    }
    public Epic(String name, String description) {
        this();
        setName(name);
        setDescription(description);
    }

    public HashMap<Integer, Subtask> getSubtasks() {
        return subtasks;
    }

    public boolean hasSubtasks() {
        return !subtasks.isEmpty();
    }

    public void calculateStatus() {
        if (subtasks.isEmpty()) {
            setStatus(TaskStatus.NEW);
            return;
        }
        boolean allSubtasksAreNew = true;
        boolean allSubtasksAreDone = true;
        for (Subtask subtask : subtasks.values()) {
            if (allSubtasksAreNew) {
                allSubtasksAreNew = subtask.getStatus() == TaskStatus.NEW;
            }
            if (allSubtasksAreDone) {
                allSubtasksAreDone = subtask.getStatus() == TaskStatus.DONE;
            }
        }
        if (allSubtasksAreNew) {
            setStatus(TaskStatus.NEW);
        } else if (allSubtasksAreDone) {
            setStatus(TaskStatus.DONE);
        } else {
            setStatus(TaskStatus.IN_PROGRESS);
        }
    }

    @Override
    public String toString() {
        String result = super.toString().replace("Task", "Epic");
        result = result.substring(0, result.length() - 1)
                + ", subtasks=" + subtasks + "}";
        return result;
    }
}
