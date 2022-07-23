package ro.andreimihalcea.food_app.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class which validates an email based on a regex pattern
 */
public class EmailValidationUtil {

    private static final String EMAIL_REGEX_PATTERN = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";

    public static final Pattern emailPattern = Pattern.compile(EMAIL_REGEX_PATTERN, Pattern.CASE_INSENSITIVE);

    /**
     * Validates the email based on the patternEmail
     *
     * @param email - the input {@link String}
     * @return a boolean
     */
    public static boolean isValidEmail(String email) {
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }
}
