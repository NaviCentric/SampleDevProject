public class UserService {

    public String authenticate(String username, String password) {
        if ("admin".equals(username) && "password1234".equals(password)) {
            return "Login successful";
        } else {
            return "Login failed";
        }
    }
}