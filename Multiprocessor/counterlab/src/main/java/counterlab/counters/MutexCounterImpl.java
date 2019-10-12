package counterlab.counters;

public class MutexCounterImpl implements Counter {

    private long count;

    @Override
    public synchronized void increment() {
        count++;
    }

    @Override
    public long getValue() {
        return count;
    }
}
