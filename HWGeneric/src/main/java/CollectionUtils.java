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

    public static<T> List<T> limit(List<? extends T> source, int size) {
        return new ArrayList<>(source.subList(0,size));
    }

    public static<T> void add(List<? super T> source, T o) {
        source.add(o);
    }

    public static<T> void removeAll(List<? super T> removeFrom, List<? extends T> c2) {
        removeFrom.removeAll(c2);
    }

    public static<T> boolean containsAll(List<? super T> c1, List<? extends T> c2) {
        return c1.containsAll(c2);
    }

    public static<T> boolean containsAny(List<? super T> c1, List<? extends T> c2) {
        for (T element: c2) {
            if (c1.contains(element)) {
                return true;
            }
        }
        return false;
    }

    public static<T extends Comparable<? super T>> List<? super T> range(List<? extends T> list, T min, T max) {
        List<? super T> newList = new ArrayList<>();
        for (T t : list) {
            if (t.compareTo(max) <= 0 && t.compareTo(min) >= 0) {
                newList.add(t);
            }
        }
        return newList;
    }

    public static<T> List<? super T> range(List<? extends T> list, T min, T max, Comparator<? super T> comparator) {
        List<? super T> newList = new ArrayList<>();
        for (T t : list) {
            if (comparator.compare(t, max) <= 0 && comparator.compare(t,min) >= 0) {
                newList.add(t);
            }
        }
        return newList;
    }
}