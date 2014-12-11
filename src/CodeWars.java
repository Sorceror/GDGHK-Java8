import java.util.stream.IntStream;

/**
 * Implementation of code wars problems at Barcamp HK 2014
 * @author Karel Petranek
 */
public class CodeWars {
    public static void main(String[] args) {
        // sum numbers bigger than 0 and less than 1000 which are divisible by 3 or 5
        IntStream naturals = IntStream.iterate(0, i -> i + 1);
        int sum = naturals.limit(1000).filter(x -> x % 3 == 0 || x % 5 == 0).sum();
        System.out.println(sum);

        // evaluate 10001st prime number
        naturals = IntStream.iterate(1, i -> i + 1);
        naturals.filter(CodeWars::isPrime).skip(10000).limit(1).forEach(System.out::println);
    }

    public static boolean isPrime(int x) {
        if (x == 1) return false;
        for (int i = 2; i <= (int) Math.sqrt(x); i++)
            if (x % i == 0)
                return false;
        return true;
    }
}