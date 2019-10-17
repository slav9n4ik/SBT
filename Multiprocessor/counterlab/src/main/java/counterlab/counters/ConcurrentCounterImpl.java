package counterlab.counters;

import java.util.concurrent.atomic.AtomicLong;

public class ConcurrentCounterImpl implements Counter {

    private AtomicLong atomicLong = new AtomicLong();

    @Override
    public void increment() {
        atomicLong.addAndGet(1);
    }

    @Override
    public long getValues() {
        return atomicLong.get();
    }
}
