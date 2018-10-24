import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CollectionUtils {
    public static<T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    public static<T> List newArrayList() {
        return new ArrayList<T>();
    }

    public static<T> int indexOf(List<? extends T> source, T o) {
        return source.indexOf(o);
    }

    public static List limit(List source, int size) {

    }

    public static<T> void add(List<? super T> source, T o) {
        source.add(o);
    }

    public static<T> void removeAll(List<? super T> removeFrom, List<T> c2) {
        removeFrom.removeAll(c2);
    }

    public static<T> boolean containsAll(List<? super T> c1, List<T> c2) {
        return c1.containsAll(c2);
    }

    public static<T> boolean containsAny(List<? super T> c1, List<T> c2) {
        return c1.conta
    }

    public static List range(List list, Object min, Object max) {
    }

    public static List range(List list, Object min, Object max, Comparator comparator) {
    }
}