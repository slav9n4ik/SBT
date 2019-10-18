package counterlab.counters;

import counterlab.lock.BakeryLock;

public class MagicCounterImpl implements Counter {

    private volatile int count;
    private BakeryLock bakeryLock;

    public MagicCounterImpl(int threadNumber) {
        bakeryLock = new BakeryLock(threadNumber);
    }

    @Override
    public void increment() {
        bakeryLock.lock();
            count = count + 1;
        //System.out.println(Thread.currentThread().getName() + " Count is: " + count);
        bakeryLock.unlock();
    }

    @Override
    public long getValues() {
        return count;
    }
}
