package counterlab.counters;

public class MagicCounterImpl implements Counter {

    private long count;

    @Override
    public void increment() {
        count += 1;
    }

    @Override
    public long getValue() {
        return count;
    }
}
