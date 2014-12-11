import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Simple example how to rewrite existing code to functional approach and gain much better readability
 * @author Pavel Janecka
 */
public class Food {
    final static String[] food = new String[]{
            "vesela mrkvicka",
            "nezbedny salatek",
            "banana",
            "krasne oranzovy pomeranc",
            "",
            "maso"
    };

    /**
     * Expected version from most Java developers
     */
    private static String summarizeNormal(String[] description) {
        final StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (String s : description) {
            if (!s.isEmpty()) {
                final String word = lastWord(s);
                if (!isFirst)
                    sb.append(" & ");
                sb.append(word);
                isFirst = false;
            }
        }

        return sb.toString();
    }

    /**
     * Slightly better version with preprocessing step which removes empty strings
     */
    private static String summarizeBetter(String[] description) {
        final List<String> nonEmpties = new ArrayList<>();
        for (String s : description) {
            if (!s.isEmpty()) {
                nonEmpties.add(s);
            }
        }

        final StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (String s : nonEmpties) {
            final String word = lastWord(s);
            if (!isFirst)
                sb.append(" & ");
            sb.append(word);
            isFirst = false;
        }

        return sb.toString();
    }

    /**
     * Version with usage of {@link StringJoiner} Java 8 class
     */
    private static String summarizeJoiner(String[] description) {
        final List<String> nonEmpties = new ArrayList<>();
        for (String s : description) {
            if (!s.isEmpty()) {
                nonEmpties.add(s);
            }
        }

        final List<String> lastWords = new ArrayList<>(nonEmpties.size());
        for (String s : nonEmpties)
            lastWords.add(lastWord(s));

        StringJoiner stringJoiner = new StringJoiner(" & ");
        for (String s : lastWords) {
            stringJoiner.add(s);
        }

        return stringJoiner.toString();
    }

    /**
     * Version with usage of {@link Streams} and it's functions
     */
    private static String summarizeFunctional(String[] description) {
        return Arrays.asList(description).stream()
                .filter((s) -> !s.isEmpty())
                .map(lastWordFunction)
                .reduce(joinOn(" & "))
                .orElse("");
    }

    private static BinaryOperator<String> joinOn(String connector) {
        return (allSoFar, nextElement) -> allSoFar + connector + nextElement;
    }

    private static BinaryOperator<String> chooseLast =
            (allSoFar, nextElement) -> nextElement;

    private static Function<String, String> lastWordFunction =
            phrase -> Arrays.asList(phrase.split(" ")).stream().reduce(chooseLast).orElse("");

    private static String lastWord(final String phrase) {
        final int lastSpace = phrase.lastIndexOf(" ");
        return lastSpace < 0 ? phrase : phrase.substring(lastSpace + 1, phrase.length());
    }

    /**
     * Version with usage of {@link Streams} and it's functions.
     * With debug output.
     */
    private static String summarizeFunctionalDebug(String[] description) {
        System.out.println("--- Stream creation");
        Stream<String> lastWordsSteam = Arrays.asList(description).stream()
                .peek(s -> System.out.println("About to filter: " + s))
                .filter((s) -> !s.isEmpty())
//                .limit(3)
                .peek(s -> System.out.println("About to map: " + s))
                .map(lastWordFunction);
        System.out.println("--- Reducing");
        return lastWordsSteam
                .peek(s -> System.out.println("About to join " + s))
                .reduce(joinOn(" & "))
                .orElse("");
    }

    /**
     * main
     */
    public static void main(String[] args) {
        final String summary = summarizeFunctionalDebug(food);
        final String expectedSummary = "mrkvicka & salatek & banana & pomeranc & maso";

        System.out.println(summary);
        if (summary.equals(expectedSummary))
            System.out.println("yes!");
    }
}