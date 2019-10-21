package counterlab.counters;

public class MagicArrayCounter implements Counter{
    private int[] values;

    public MagicArrayCounter(int threadNumber) {
        values = new int[threadNumber * 100];
        for (int i = 0; i < threadNumber; i++) {
            values[i] = 0;
        }
    }

    @Override
    public void increment() {
        int id = getCurrentThreadIntID();
        values[id] = values[id] + 1;
    }

    @Override
    public long getValues() {
        int sum = 0;
        for (int i : values) {
            sum += i;
        }
        return sum;
    }

    private int getCurrentThreadIntID() {
        return Long.hashCode(Thread.currentThread().getId()) % (values.length * 100);
    }
}
