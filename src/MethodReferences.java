import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Example of static method references
 * @author Pavel Janecka
 */
public class MethodReferences {
    private List<Person> persons = new ArrayList<>();

    public MethodReferences() {
        persons.add(new Person("Pavel", "Janecka"));
        persons.add(new Person("Jan", "Novak"));
        persons.add(new Person("John", "Smith"));
        persons.add(new Person("Petr", "Novak"));
        persons.add(new Person("Johnathan", "Smith"));

        // Java 8
        persons.sort(Comparator.comparing(Person::getLastName).thenComparing(Person::getFirstName));
        persons.forEach(System.out::println);

        System.out.println();

        // Java 7
        persons.sort(new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                int n = p1.getLastName().compareTo(p2.getLastName());
                if (n == 0) {
                    return p1.getFirstName().compareTo(p2.getFirstName());
                }
                return n;
            }
        });
        for (Person p : persons)
            System.out.println(p);
    }

    /**
     * Helper class which contains whole firstName of one person
     * @author Pavel Janecka
     */
    private class Person {
        private String firstName;
        private String lastName;

        private Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        new MethodReferences();
    }
}
