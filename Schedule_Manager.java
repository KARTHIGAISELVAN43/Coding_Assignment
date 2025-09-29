
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ScheduleManager {
    private static ScheduleManager instance;
    private List<Task> tasks;
    private List<ConflictObserver> observers;

    private ScheduleManager() {
        tasks = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public static synchronized ScheduleManager getInstance() {
        if (instance == null) {
            instance = new ScheduleManager();
        }
        return instance;
    }

    public void addObserver(ConflictObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(String message) {
        for (ConflictObserver observer : observers) {
            observer.notifyConflict(message);
        }
    }

    public String addTask(Task task) {
        for (Task t : tasks) {
            if (!(task.getEndTime().isBefore(t.getStartTime()) || task.getStartTime().isAfter(t.getEndTime()))) {
                notifyObservers("Task conflicts with existing task \"" + t.getDescription() + "\"");
                return "Error: Task conflicts with existing task \"" + t.getDescription() + "\"";
            }
        }
        tasks.add(task);
        tasks.sort(Comparator.comparing(Task::getStartTime));
        return "Task added successfully. No conflicts.";
    }

    public String removeTask(String description) {
        for (Task t : tasks) {
            if (t.getDescription().equalsIgnoreCase(description)) {
                tasks.remove(t);
                return "Task removed successfully.";
            }
        }
        return "Error: Task not found.";
    }

    public String viewTasks() {
        if (tasks.isEmpty()) {
            return "No tasks scheduled for the day.";
        }
        StringBuilder sb = new StringBuilder();
        for (Task t : tasks) {
            sb.append(t.toString()).append("\n");
        }
        return sb.toString();
    }
}
