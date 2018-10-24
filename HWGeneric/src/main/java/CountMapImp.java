import java.util.HashMap;
import java.util.Map;

public class CountMapImp<E> implements CountMap<E> {

//    private static final int DEFAULT_CAPACITY = 10;
      private Map<E,Integer> elements;
      private Map<E,Integer> source;
//    transient Object[] elementData;
//    private int size;
//    private int index;
//    private int length;
//    private int diffElement;

    public CountMapImp() {
        elements = new HashMap<>();
//        this.elementData = new Object[DEFAULT_CAPACITY];
//        this.size = DEFAULT_CAPACITY;
//        this.index = -1;
//        this.length = 0;
//        this.diffElement = 0;
    }

    @Override
    public void add(E e) {
//        boolean flag = false;
//        if (elementData.length == size) {
//            makeNewCapicity();
//        }
//        for (int i = 0; i < length; i++) {
//            if(e.equals(elementData[i])) {
//                flag = true;
//                break;
//            }
//        }
//        if (!flag) {
//            diffElement++;
//        }
//        elementData[++index] = e;
//        length++;
        if (elements.containsKey(e)) {
            int value = elements.get(e);
            elements.put(e, ++value);
        } else {
            elements.put(e, 1);
        }
    }

    @Override
    public int getCount(E e) {
//        int c = 0;
//        for (int i = 0; i < length; i++) {
//            if (e.equals(elementData[i])) {
//                c++;
//            }
//        }
//        return c;
        return elements.get(e);
    }

    @Override
    public int remove(E e) {
//        int count = 0;
//        int i = 0;
//        while(i < length) {
//            if (e.equals(elementData[i])) {
//                deleteElement(i);
//                count++;
//                index--;
//            } else {
//                i++;
//            }
//        }
//        return count;
        int count = elements.get(e);
        elements.remove(e);
        return count;
    }

    @Override
    public int size() {
//        return diffElement;
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

    //    private void makeNewCapicity() {
//        size = size*2;
//        Object[] buf = new Object[size];
//        for (int i = 0; i < size/2; i++) {
//            buf[i] = elementData[i];
//        }
//        elementData = buf;
//    }
//
//    private void deleteElement(int index) {
//        for (int i = index; i < length -1; i++) {
//            elementData[i] = elementData[i+1];
//        }
//        elementData[length-1] = null;
//        length--;
//    }

}
