import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Example with sales which shows multiple new things in Java 8 including lambda,
 * function as first-class citizen and streams.
 * @author Pavel Janecka
 */
public class Sales {
    static final List<Sale> sales = Arrays.asList(
            new Sale(Store.Hradec, LocalDateTime.now(),
                    Arrays.asList(
                            new Item("mrkev", 50)
                    )),
            new Sale(Store.Praha, LocalDateTime.now(),
                    Arrays.asList(
                            new Item("mrkev", 70),
                            new Item("maslo", 90)
                    )),
            new Sale(Store.Brno, LocalDateTime.now().minus(1, ChronoUnit.DAYS),
                    Arrays.asList(
                            new Item("houska", 5),
                            new Item("mrkev", 50),
                            new Item("maslo", 30)
                    ))
    );

    public static Stream<Sale> saleStream() {
        return sales.stream();
//        return RandomSale.streamOf(100);
//        return RandomSale.streamOf(10000000).parallel();
    }

    public static void main(String[] args) {
        LocalTime startTime = LocalTime.now();

        // how many sales today ?
        System.out.println("Today sales: " + saleStream().filter(sale -> sale.dateAndTime.toLocalDate().isEqual(LocalDate.now())).count());

        // was there a big sale? (> 100)
        System.out.println("Big sale today? : " + saleStream().anyMatch(sale -> sale.total() > 100));

        // maximum sale?
        DoubleSummaryStatistics statistics = saleStream().mapToDouble(Sale::total).summaryStatistics();
        System.out.println("Max sale: " + statistics.getMax());

        Supplier<Stream<Item>> itemStream = () -> saleStream().flatMap(sales -> sales.items.stream());
        // how many items were sold already?
        System.out.println("Count of items: " + itemStream.get().count());

        // how may distinct items?
        System.out.println("Distinct items: " + itemStream.get().map(item -> item.name).distinct().count());

        // which items was it?
        System.out.println("Distinct items: " + itemStream.get().map(item -> item.name).distinct().collect(Collectors.joining(", ")));

        // summarize sales by store
        Map<Store, DoubleSummaryStatistics> summary = saleStream().collect(Collectors.groupingBy(sale -> sale.store, Collectors.summarizingDouble(Sale::total)));
        System.out.println("Summary by store: " + summary);
        summary.keySet().stream().forEach(store -> System.out.println(store + " stats: " + summary.get(store)));

//        // make it parallel
//        ConcurrentMap<String, DoubleSummaryStatistics> summaryThread = saleStream()
//                .collect(Collectors.groupingByConcurrent(sale -> Thread.currentThread().getName(), Collectors.summarizingDouble(Sale::total)));
//        System.out.println("Summary by store: " + summaryThread);
//        summaryThread.keySet().stream().sorted().forEach(store -> System.out.println(store + " stats: " + summaryThread.get(store)));

        LocalTime endTime = LocalTime.now();
        System.out.println("Took: " + Duration.between(startTime, endTime));
    }

    public static enum Store {
        Hradec, Praha, Brno
    }

    public static class Sale {
        private Store store;
        private LocalDateTime dateAndTime;
        private List<Item> items;

        public Sale(Store store, LocalDateTime dateAndTime, List<Item> items) {
            this.store = store;
            this.dateAndTime = dateAndTime;
            this.items = items;
        }

        public double total() {
            return items.stream().mapToDouble(item -> item.price).sum();
        }
    }

    public static class Item {
        private String name;
        private int price;

        private Random random = new Random();
        private List<String> allItems = Arrays.asList("rohlik", "houska", "maslo", "chleba", "jogurt", "ovoce", "zelenina", "maso", "napoj");

        public Item(String name, int price) {
            this.name = name;
            this.price = price;
        }

        public Item() {
            this.name = allItems.get(random.nextInt(allItems.size()));
            this.price = random.nextInt(200);
        }
    }

    public static class RandomSale {
        private static Random random = new Random();

        public static Stream<Sale> streamOf(long quantity) {
            return Stream.generate(supplier).limit(quantity);
        }

        private static Supplier<Sale> supplier = () -> new Sale(
                pickStore(),
                pickDate(),
                randomListOfItems()
        );

        private static Store pickStore() {
            return Arrays.asList(Store.values()).get(random.nextInt(Store.values().length));
        }

        private static LocalDateTime pickDate() {
            return LocalDateTime.now().plus(random.nextInt(14) - 7, ChronoUnit.DAYS);
        }

        private static List<Item> randomListOfItems() {
            return Stream.generate(Item::new).limit(random.nextInt(10)).collect(Collectors.toList());
        }
    }
}
