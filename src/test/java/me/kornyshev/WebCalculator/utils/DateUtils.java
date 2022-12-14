package me.kornyshev.WebCalculator.utils;

import org.apache.commons.lang3.StringUtils;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtils {

    private static final String CURRENT_MONTH = "$MONTH";
    private static final String CURRENT_YEAR = "$YEAR";
    private static final String CURRENT_DAY = "$DAY";
    private static final String LAST_DAY = "$LAST_DAY";
    private static final String TODAY = "$TODAY";
    private static final String DYNAMIC_DATE_PATTERN = "\\S*[$][^-]\\S+[^.,\\s]";
    private static final String DYNAMIC_TODAY_YEAR_PATTERN = "[$][^-]\\S+[^.,\\s]\\w";
    private static final String AC_YEAR = "$AC_YEAR";

    private DateUtils() {
    }

    public static String replaceAllDynamicDatesInString(String string, DateFormat dateFormat) {
        String result = string;
        Matcher matcher = doesStringHaveDynamicTodayOrYear(string) ? Pattern.compile(DYNAMIC_TODAY_YEAR_PATTERN).matcher(string) :
                Pattern.compile(DYNAMIC_DATE_PATTERN).matcher(string);
        while (matcher.find()) {
            String substring = matcher.group();
            String replacement = processDateFromString(substring, dateFormat.getPattern());
            result = result.replace(substring, Objects.requireNonNull(replacement));
        }
        return result;
    }

    private static boolean doesStringHaveDynamicTodayOrYear(String string) {
        return string.contains(TODAY)
                || string.startsWith(AC_YEAR)
                || string.startsWith(CURRENT_YEAR)
                || string.contains(" " + AC_YEAR)
                || string.contains(" " + CURRENT_YEAR);
    }

    /**
     * Examples (28.04.2021):
     * <p>$MONTH-5/$DAY-10/$YEAR-2 ---> 2018 Nov 18</p>
     * <p>$MONTH/$DAY/$YEAR ---> 2021 Apr 28</p>
     * <p>$MONTH-1/$DAY-10/$YEAR ---> 2021 Mar 18</p>
     * <p>$MONTH-4/$DAY+5/$YEAR ---> 2021 Jan 02</p>
     * <p>$MONTH-12/$DAY/$YEAR ---> 2020 Apr 28</p>
     * <p>$MONTH-2/$LAST_DAY-10/$YEAR ---> 2021 Feb 28</p>
     * <p>$MONTH-2/$LAST_DAY-10/$YEAR-1 ---> 2020 Feb 29</p>
     * <p>$MONTH-1/$DAY+10/$YEAR ---> 2021 Apr 07</p>
     * <p>$TODAY</p>
     * <p>01/01/$AC_YEAR-1 ---> 2020 Jan 01</p>
     * <p>$YEAR-1 --->2021</p>
     * <p>$AC_YEAR+2 ---> 2024</p>
     */
    public static String processDateFromString(String string, String pattern) {
        LocalDateTime result = LocalDateTime.from(new Date().toInstant().atZone(ZoneId.of("UTC")));
        if (Objects.isNull(string)) {
            return null;
        }
        if (string.isEmpty()) {
            return StringUtils.EMPTY;
        }
        if (string.contains(TODAY)) {
            int offset = extractOffset(string, TODAY);
            return changeDayPerOffset(result, offset).format(DateTimeFormatter.ofPattern(pattern).withLocale(Locale.ENGLISH));
        }
        if (string.startsWith(CURRENT_YEAR)) {
            return processDefinedYear(result, string, CURRENT_YEAR);
        }
        String[] preparedDate = string.split("/");
        final int MONTH_INDEX = 0;
        final int DAY_INDEX = 1;
        final int YEAR_INDEX = 2;

        result = preparedDate[MONTH_INDEX].startsWith(CURRENT_MONTH)
                ? extractOffset(preparedDate[MONTH_INDEX], CURRENT_MONTH) >= 0
                ? result.plusMonths(extractOffset(preparedDate[MONTH_INDEX], CURRENT_MONTH))
                : result.minusMonths(Math.abs(extractOffset(preparedDate[MONTH_INDEX], CURRENT_MONTH)))
                : result.withMonth(Integer.parseInt(preparedDate[MONTH_INDEX]));

        result = preparedDate[YEAR_INDEX].startsWith(CURRENT_YEAR)
                ? changeYearPerOffset(result, extractOffset(preparedDate[YEAR_INDEX], CURRENT_YEAR))
                : result.withYear(Integer.parseInt(preparedDate[YEAR_INDEX]));

        if (preparedDate[DAY_INDEX].startsWith(CURRENT_DAY)) {
            result = changeDayPerOffset(result, extractOffset(preparedDate[DAY_INDEX], CURRENT_DAY));
        } else if (preparedDate[DAY_INDEX].startsWith(LAST_DAY)) {
            result = setLastDayOfMonth(result);
        } else {
            result = result.withDayOfMonth(Integer.parseInt(preparedDate[DAY_INDEX]));
        }
        return result.format(DateTimeFormatter.ofPattern(pattern).withLocale(Locale.ENGLISH));
    }

    public static String processDateFromString(String string, DateFormat dateFormat) {
        return processDateFromString(string, dateFormat.getPattern());
    }

    private static int extractOffset(String value, String parameter) {
        return value.length() != parameter.length() ? Integer.parseInt(value.substring(parameter.length())) : 0;
    }

    private static LocalDateTime setLastDayOfMonth(LocalDateTime date) {
        int requiredDay = 31;
        if (date.getMonth() == Month.FEBRUARY) {
            requiredDay = 29;
        }
        try {
            return date.withDayOfMonth(requiredDay);
        } catch (DateTimeException dte) {
            return date.withDayOfMonth(requiredDay - 1);
        }
    }

    private static String processDefinedYear(LocalDateTime result, String string, String regYear) {
        return changeYearPerOffset(result, extractOffset(string, regYear)).format(DateTimeFormatter.ofPattern("yyyy").withLocale(Locale.ENGLISH));
    }

    private static LocalDateTime changeDayPerOffset(LocalDateTime result, int offset) {
        return offset >= 0 ? result.plusDays(offset) : result.minusDays(Math.abs(offset));
    }

    private static LocalDateTime changeYearPerOffset(LocalDateTime result, int offset) {
        return offset >= 0 ? result.plusYears(offset) : result.minusYears(Math.abs(offset));
    }

    public enum DateFormat {

        DAY_MONTH_YEAR("dd MMM yyyy"),
        DAY_FULL_MONTH_YEAR("dd MMMM YYYY"),
        DAY_MONTH_YEAR_HYPHEN("dd-MMM-yyyy"),
        YEAR("yyyy"),
        MONTH("MMM"),
        DAY_MONTH("dd MMM"),
        DAY_FULL_MONTH("dd MMMM"),
        SHORT_MONTH_DAY_YEAR("M/d/yyyy"),
        YEAR_MONTH_DAY_TIME_HOUR_MINUTE_SECOND_MS("yyyy-MM-dd 00:00:00.0000000"),
        YEAR_MONTH_DAY_TIME_HOUR_MINUTE_SECOND_DS("yyyy-MM-dd 00:00:00.0"),
        YEAR_MONTH_DAY_TIME_HOUR_MINUTE_SECOND("yyyy-MM-dd HH:mm:ss"),
        YEAR_MONTH_DAY_HYPHEN("yyyy-MM-dd"),
        MONTH_YEAR("MMM yyyy"),
        FULL_MONTH_YEAR("MMMM yyyy"),
        MONTH_YEAR_SHORT("MMM yy"),
        MONTH_YEAR_SHORT_HYPHEN("MMM-yy"),
        MONTH_DAY_YEAR_SLASH("MM/dd/yyyy"),
        QUARTER_YEAR("qqq yyyy"),
        YEAR_MONTH("yyyy-M");

        private final String pattern;

        DateFormat(String datePattern) {
            this.pattern = datePattern;
        }

        public String getPattern() {
            return this.pattern;
        }

    }

}
