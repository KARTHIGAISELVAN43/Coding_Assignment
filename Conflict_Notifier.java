

public class ConflictNotifier implements ConflictObserver {
    @Override
    public void notifyConflict(String message) {
        System.out.println("Conflict Alert: " + message);
    }
}
