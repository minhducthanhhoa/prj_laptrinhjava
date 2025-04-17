package ra.edu.validate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class Validator {
    private static final String EMAIL_REGEX = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$";
    private static final String PHONE_REGEX = "^(\\+84|0)(3[2-9]|5[6|8|9]|7[0|6-9]|8[1-5]|9[0-9])\\d{7}$";
    private static final String DATE_FORMAT = "dd/MM/yyyy";

    public static boolean isNotBlank(String input) {
        return input != null && !input.trim().isEmpty();
    }

    public static boolean isValidEmail(String email) {
        return email != null && Pattern.matches(EMAIL_REGEX, email);
    }

    public static boolean isValidPhone(String phone) {
        return phone != null && Pattern.matches(PHONE_REGEX, phone);
    }

    public static boolean isValidDate(String dateStr) {
        if (dateStr == null || dateStr.isEmpty())
            return false;
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static Date parseDate(String dateStr) {
        if (!isValidDate(dateStr)) return null;
        try {
            return new SimpleDateFormat(DATE_FORMAT).parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    public static boolean isPositiveInt(int number) {
        return number > 0;
    }

    public static boolean isNonNegativeInt(int number) {
        return number >= 0;
    }
}
