package counterlab.counters;

public class MutexCounterImpl implements Counter {

    //Volatile и Synchronized getValue не нужны, тк читаем все равно одним потоком после того, как все записали
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
