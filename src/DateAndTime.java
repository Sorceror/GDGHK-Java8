import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

/**
 * Example of creation and formatting of date and time in Java 8
 * @author Pavel Janecka
 */
public class DateAndTime {

    public DateAndTime() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        LocalDateTime future = now.plus(2, ChronoUnit.WEEKS).minus(17, ChronoUnit.HOURS).plusMinutes(15);
        System.out.println(future);

        Period period = Period.between(now.toLocalDate(), future.toLocalDate());
        System.out.println(period);

        Duration duration = Duration.between(now.toLocalTime(), future.toLocalTime());
        System.out.println(duration);

        LocalDateTime anotherFuture = future.with(TemporalAdjusters.firstDayOfNextMonth()).with(TemporalAdjusters.nextOrSame(DayOfWeek.WEDNESDAY));
        System.out.println(anotherFuture);

        DateTimeFormatter dtf = new DateTimeFormatterBuilder()
                .appendLiteral("den: ").appendValue(ChronoField.DAY_OF_MONTH).appendLiteral(" ~ ")
                .appendLiteral("mesic: ").appendValue(ChronoField.MONTH_OF_YEAR).appendLiteral(" ~ ")
                .appendLiteral("rok: ").appendValue(ChronoField.YEAR).toFormatter();
        System.out.println(dtf.format(anotherFuture));
    }

    public static void main(String[] args) {
        new DateAndTime();
    }
}
