
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class TaskFactory {
    public static Task createTask(String description, String start, String end, String priority) throws IllegalArgumentException {
        try {
            LocalTime startTime = LocalTime.parse(start);
            LocalTime endTime = LocalTime.parse(end);

            if (endTime.isBefore(startTime) || startTime.equals(endTime)) {
                throw new IllegalArgumentException("End time must be after start time.");
            }

            return new Task(description, startTime, endTime, priority);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid time format. Use HH:MM format.");
        }
    }
}
