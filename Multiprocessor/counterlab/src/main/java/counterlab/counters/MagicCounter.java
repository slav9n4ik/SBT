package counterlab.counters;

public class MagicCounter implements Counter {

    private volatile long count;

    @Override
    public void increment() {
    }

    @Override
    public long getValue() {
        return 0;
    }
}
