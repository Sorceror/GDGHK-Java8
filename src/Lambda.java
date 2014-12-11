import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Example of lambda usage in comparator interface
 * @author Pavel Janecka
 */
public class Lambda {

    public Lambda() {
        Integer[] numbers = {2, 7, 4, -1, 9, 12, -3, 5, -4, 0};
        System.out.println("Original\n" + Arrays.toString(numbers));

        // LAMBDA EXAMPLE ON COMPARATOR
        System.out.println("Sorted");

        // Java 8
        Arrays.sort(numbers, (n1, n2) -> n1 - n2);
        System.out.println(Arrays.toString(numbers));

        // Java 7
        Arrays.sort(numbers, new Comparator<Integer>() {
            @Override
            public int compare(Integer n1, Integer n2) {
                return n1 - n2;
            }
        });
        System.out.println(Arrays.toString(numbers));

        // ITERATION ON COLLECTION ELEMENTS
        List<Integer> numberList = Arrays.asList(numbers);

        // Java 8
        numberList.forEach(n -> System.out.println(n));
        // alternative way
        numberList.forEach(System.out::println);

        System.out.println();

        // Java 7
        for (Integer n : numberList) {
            System.out.println(n);
        }
    }

    public static void main(String[] args) {
        new Lambda();
    }
}
