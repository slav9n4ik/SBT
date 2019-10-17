package counterlab.counters;

import counterlab.BakeryLock;

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
        //System.out.println("I am " + Thread.currentThread().getId() % 4 + " and count is: " + count);
        bakeryLock.unlock();
    }

    @Override
    public long getValue() {
        return count;
    }
}
