package counterlab.counters;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class MagicArray2Counter implements Counter {
    private List<Integer> value;
    CountDownLatch countDownLatch;

    public MagicArray2Counter(Integer threadNumber, CountDownLatch countDownLatch) {
        this.value = new ArrayList<>(threadNumber);
        this.countDownLatch = countDownLatch;
    }

    public void initInCounter() {
        synchronized (this) {
            String newId = String.valueOf(value.size());
            Thread.currentThread().setName(newId);
            value.add(0);
            countDownLatch.countDown();
        }
    }


    @Override
    public void increment() {
        int id = getCurrentThreadIntID();
        int newValue = value.get(id) + 1;
        value.set(id, newValue);
    }

    @Override
    public long getValues() {
        int sum = 0;
        for (int i : value) {
            sum += i;
        }
        return sum;
    }

    private int getCurrentThreadIntID() {
        return Integer.parseInt(Thread.currentThread().getName());
    }

    public List<Integer> getValue() {
        return value;
    }
}
