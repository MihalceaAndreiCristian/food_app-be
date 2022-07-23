package ro.andreimihalcea.food_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ro.andreimihalcea.food_app.exception.item.ItemAlreadySavedException;
import ro.andreimihalcea.food_app.exception.item.ItemNotFoundException;
import ro.andreimihalcea.food_app.exception.order.OrderFailedException;
import ro.andreimihalcea.food_app.exception.user.UserNotFoundException;
import ro.andreimihalcea.food_app.exception.user.UsernameHasBeenTakenException;
import ro.andreimihalcea.food_app.util.JsonUtil;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    public static final String MESSAGE = "Message";

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> nullPointerException(NullPointerException nullPointerExe) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put(MESSAGE, nullPointerExe.getMessage());
        return new ResponseEntity<>(JsonUtil.objectToJsonString(result), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> userNotFoundException(UserNotFoundException nullPointerExe) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put(MESSAGE, nullPointerExe.getMessage());
        return new ResponseEntity<>(JsonUtil.objectToJsonString(result), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsernameHasBeenTakenException.class)
    public ResponseEntity<Object> usernameHasBeenTakenException(UsernameHasBeenTakenException nullPointerExe) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put(MESSAGE, nullPointerExe.getMessage());
        return new ResponseEntity<>(JsonUtil.objectToJsonString(result), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ItemAlreadySavedException.class)
    public ResponseEntity<Object> itemAlreadySavedException(ItemAlreadySavedException nullPointerExe) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put(MESSAGE, nullPointerExe.getMessage());
        return new ResponseEntity<>(JsonUtil.objectToJsonString(result), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<Object> itemNotFoundException(ItemNotFoundException nullPointerExe) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put(MESSAGE, nullPointerExe.getMessage());
        return new ResponseEntity<>(JsonUtil.objectToJsonString(result), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderFailedException.class)
    public ResponseEntity<Object> orderFailedException(OrderFailedException nullPointerExe) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put(MESSAGE, nullPointerExe.getMessage());
        return new ResponseEntity<>(JsonUtil.objectToJsonString(result), HttpStatus.BAD_REQUEST);
    }

}
