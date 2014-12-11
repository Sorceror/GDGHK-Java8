import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Example of usage {@link Predicate} functional interface for filtering list of numbers
 * @author Pavel Janecka
 */
public class FunctionalInterface {

    public FunctionalInterface() {
        Integer[] numbers = {2, 7, 4, -1, 9, 12, -3, 5, -4, 0};
        List<Integer> numbersList = Arrays.asList(numbers);

        // print all natural numbers
        filter(numbersList, n -> n > 0);
        System.out.println();
        // print even numbers
        filter(numbersList, (n -> n % 2 == 0));
    }

    /**
     * Filter selected list with selected predicate and print elements that fulfill predicate condition
     * @param list {@link List} of {@link Integer}
     * @param condition {@link Predicate}
     */
    private void filter(List<Integer> list, Predicate<Integer> condition) {
        list.forEach(n -> {
            if (condition.test(n))
                System.out.println(n);
        });
    }

    public static void main(String[] args) {
        new FunctionalInterface();
    }
}
