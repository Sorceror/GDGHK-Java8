import java.util.Iterator;

/**
 * Iterable interface implementation in Java 8. Shows how does work default and static methods in interfaces.
 * @author Pavel Janecka
 */
public interface Iterable<T> {
    Iterator<T> iterator();

    // for some reason does not compile in idea
//    default void forEach(Consumer<? super T> action) {
//        Objects.requireNonNull(action);
//        for (T t : this) {
//            action.accept(t);
//        }
//    }

    static void fooUtil(String bar) {
        // some implementation
    }
}
