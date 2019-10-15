package counterlab.counters;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockCounterImpl implements Counter {

    private final Lock locker;
    private volatile long count;

    public LockCounterImpl() {
        this.locker = new ReentrantLock();
        this.count = 0;
    }

    @Override
    public void increment() {
        locker.lock();
        try {
            count++;
        }
        finally {
            locker.unlock();
        }
    }

    @Override
    public long getValue() {
        return count;
    }
}
