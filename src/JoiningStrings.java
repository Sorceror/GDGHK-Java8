import java.util.StringJoiner;
import java.util.stream.Stream;

/**
 * Example of usage new Java 8 {@link StringJoiner} class
 * @author Pavel Janecka
 */
public class JoiningStrings {

    public JoiningStrings() {
        StringJoiner stringJoiner = new StringJoiner(", ", "{", "}");
        Stream.of(2, 7, 4, -1, 9, 12, -3, 5, -4, 0).map((n) -> Integer.toString(n)).forEach(stringJoiner::add);
        System.out.println(stringJoiner);

        StringJoiner anotherJoiner = new StringJoiner("|");
        anotherJoiner.add("test");
        anotherJoiner.add("test 2");

        System.out.println(stringJoiner.merge(anotherJoiner));

        System.out.println(String.join(" ~ ", "string 1", "string 2", "string 3"));
    }

    public static void main(String[] args) {
        new JoiningStrings();
    }
}
