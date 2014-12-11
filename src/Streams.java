import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Examples of creation and usage of {@link Streams} in Java 8
 * @author Pavel Janecka
 */
public class Streams {

    public Streams() throws Exception {
        // Stream of integers
        List<Integer> numbersList = Arrays.asList(2, 7, 4, -1, 9, 12, -3, 5, -4, 0);

        Stream numbersStream = numbersList.stream();
        Stream numbersParallelStream = numbersList.parallelStream();

        // Stream of file content (from Reader)
        System.out.println("Current path: " + Paths.get(".").toAbsolutePath());
        System.out.println("File content: ");
        try (Stream<String> lipsum = Files.lines(Paths.get("Java8/lipsum.txt"))) {
            lipsum.forEach(System.out::println);
        }

        // Walking the file tree and get summary statistics of all files under root folder
        try (Stream<Path> files = Files.walk(Paths.get("."))) {
            System.out.println(files.mapToLong((path) -> {
                try (Stream lines = Files.lines(path)) {
                    return lines.count();
                } catch (Exception e) {
                    return 0l;
                }
            }).summaryStatistics());
        }

        // 100 random number between 0 and 100
        System.out.println(
                Stream.generate(Math::random).limit(100).map(n -> (int) (n * 100)).collect(Collectors.toList())
        );

        // Prints numbers from 1 to 10
        IntStream.range(1, 11).forEach(System.out::println);
        // alternative way
        Stream.iterate(1, (i) -> i + 1).limit(10).forEach(System.out::println);

        // Stream from array
        Stream.of(1, 2, 3, 4, 5);
    }

    public static void main(String[] args) {
        try {
            new Streams();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
