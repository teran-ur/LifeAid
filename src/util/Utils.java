package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils {
    public static boolean validDate(String s) {
        try {
            LocalDate.parse(s, DateTimeFormatter.ISO_LOCAL_DATE);
            return true;
        } catch (Exception e) { return false; }
    }
}
