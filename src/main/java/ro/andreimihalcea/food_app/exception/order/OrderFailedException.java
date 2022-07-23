package ro.andreimihalcea.food_app.exception.order;

public class OrderFailedException extends RuntimeException{
    public OrderFailedException(String message) {
        super(message);
    }
}
