import java.util.HashMap;
import java.util.Map;

public class UserService {

    private Map<String, Integer> failedAttempts = new HashMap();
    private static final int MAX_FAILED_ATTEMPTS = 4;

    public String authenticate(String username, String password) {
        if (isUserBlocked(username)) {
            return "User is blocked due to too many failed login attempts.";
        }

        if ("admin".equals(username) && "password1234".equals(password)) {
            resetFailedAttempts(username); // Reset attempts on successful login
            return "Login successful";
        } else {
            incrementFailedAttempts(username);
            if (failedAttempts.get(username) >= MAX_FAILED_ATTEMPTS) {
                return "User is blocked due to too many failed login attempts.";
            } else {
                return "Login failed. You have " + (MAX_FAILED_ATTEMPTS - failedAttempts.get(username)) + " attempts left.";
            }
        }
    }

    private void incrementFailedAttempts(String username) {
        failedAttempts.put(username, failedAttempts.getOrDefault(username, 0) + 1);
    }

    private void resetFailedAttempts(String username) {
        failedAttempts.put(username, 0);
    }

    private boolean isUserBlocked(String username) {
        return failedAttempts.getOrDefault(username, 0) >= MAX_FAILED_ATTEMPTS;
    }
}
