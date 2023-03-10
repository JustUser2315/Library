package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormatHelper {
    private static final String PATTERN = "yyyy-MM-dd";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);

    public static LocalDate format (String date){
        return LocalDate.parse(date, FORMATTER);
    }
}
