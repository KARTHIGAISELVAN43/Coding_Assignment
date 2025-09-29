

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class LoggerUtility {
    private static final String LOG_FILE = "scheduler.log";

    public static void log(String message) {
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write(LocalDateTime.now() + " - " + message + "\n");
        } catch (IOException e) {
            System.out.println("Logging error: " + e.getMessage());
        }
    }
}
