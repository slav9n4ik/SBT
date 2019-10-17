package counterlab.counters;

import counterlab.lock.BakeryLock;

public class MagicCounterImpl implements Counter {

    private long count;
    private BakeryLock bakeryLock;

    public MagicCounterImpl(int threadNumber) {
        bakeryLock = new BakeryLock(threadNumber);
    }

    @Override
    public void increment() {
        bakeryLock.lock();
            count += 1;
        System.out.println("Count is: " + count);
        bakeryLock.unlock();
    }

    @Override
    public long getValues() {
        return count;
    }
}
