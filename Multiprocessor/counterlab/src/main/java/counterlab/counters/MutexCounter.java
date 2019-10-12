package counterlab.counters;

import counterlab.Counter;

public class MutexCounter implements Counter {

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
