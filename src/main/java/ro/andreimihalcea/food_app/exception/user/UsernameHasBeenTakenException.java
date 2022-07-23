package ro.andreimihalcea.food_app.exception.user;

public class UsernameHasBeenTakenException extends RuntimeException{
    public UsernameHasBeenTakenException(String message) {
        super(message);
    }
}
