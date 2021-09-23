package Login;

public class UserNotExistExcpt extends Exception{

    public UserNotExistExcpt(String message) {
        super(message);
    }
}
