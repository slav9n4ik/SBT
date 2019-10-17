package counterlab.counters;

import counterlab.BakeryHashLock;

public class MagicCounter2Impl implements Counter {
    private long count;
    private BakeryHashLock bakeryLock;

    public MagicCounter2Impl(int threadNumber) {
        bakeryLock = new BakeryHashLock(threadNumber);
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
