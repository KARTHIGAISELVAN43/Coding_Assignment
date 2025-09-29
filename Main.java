
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ScheduleManager manager = ScheduleManager.getInstance();
        manager.addObserver(new ConflictNotifier());

        System.out.println("Astronaut Daily Schedule Organizer");
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. View Tasks");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter description: ");
                    String desc = sc.nextLine();
                    System.out.print("Enter start time (HH:MM): ");
                    String start = sc.nextLine();
                    System.out.print("Enter end time (HH:MM): ");
                    String end = sc.nextLine();
                    System.out.print("Enter priority (High/Medium/Low): ");
                    String priority = sc.nextLine();

                    try {
                        Task task = TaskFactory.createTask(desc, start, end, priority);
                        String result = manager.addTask(task);
                        LoggerUtility.log("Add Task - " + desc + " : " + result);
                        System.out.println(result);
                    } catch (IllegalArgumentException e) {
                        LoggerUtility.log("Add Task Failed - " + e.getMessage());
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Enter description to remove: ");
                    String toRemove = sc.nextLine();
                    String removeResult = manager.removeTask(toRemove);
                    LoggerUtility.log("Remove Task - " + toRemove + " : " + removeResult);
                    System.out.println(removeResult);
                    break;

                case 3:
                    System.out.println(manager.viewTasks());
                    LoggerUtility.log("Viewed Tasks");
                    break;

                case 4:
                    LoggerUtility.log("Application closed by user.");
                    System.out.println("Exiting... Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
