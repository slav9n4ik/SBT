package counterlab.counters;

import counterlab.Counter;

import java.util.concurrent.atomic.AtomicLong;

public class ConcurrentCounter implements Counter {

    private AtomicLong atomicLong = new AtomicLong();

    @Override
    public void increment() {
        atomicLong.addAndGet(1);
    }

    @Override
    public long getValue() {
        return atomicLong.get();
    }
}
