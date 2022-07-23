package ro.andreimihalcea.food_app.exception.item;

public class ItemAlreadySavedException extends RuntimeException{
    public ItemAlreadySavedException(String message) {
        super(message);
    }
}
