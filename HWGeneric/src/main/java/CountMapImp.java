import java.util.HashMap;
import java.util.Map;

public class CountMapImp<E> implements CountMap<E> {

      private Map<E,Integer> elements;
      private Map<E,Integer> source;

    public CountMapImp() {
        elements = new HashMap<>();
    }

    @Override
    public void add(E e) {
        if (elements.containsKey(e)) {
            int value = elements.get(e);
            elements.put(e, ++value);
        } else {
            elements.put(e, 1);
        }
    }

    @Override
    public int getCount(E e) {
        return elements.get(e);
    }

    @Override
    public int remove(E e) {
        int count = elements.get(e);
        elements.remove(e);
        return count;
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public void addAll(CountMap<E> source) {
        this.source = source.toMap();
        for (Map.Entry<E, Integer> entry : this.source.entrySet()) {
            if (elements.containsKey(entry.getKey())) {
                elements.put(entry.getKey(), elements.get(entry.getKey()) + entry.getValue());
            } else {
                elements.put(entry.getKey(), entry.getValue());
            }
        }
    }

    @Override
    public Map toMap() {
        return elements;
    }

    @Override
    public void toMap(Map destination) {
        this.source = destination;
        for (Map.Entry<E, Integer> entry : elements.entrySet()) {
            if (source.containsKey(entry.getKey())) {
                source.put(entry.getKey(), source.get(entry.getKey()) + entry.getValue());
            } else {
                source.put(entry.getKey(), entry.getValue());
            }
        }
    }

}
