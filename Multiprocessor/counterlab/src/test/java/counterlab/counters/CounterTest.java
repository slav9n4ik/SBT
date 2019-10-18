package counterlab.counters;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CounterTest {

    private static final int threadNumber = 4;
    private static final int incrementCallsCount = 1_000_000;

    @Test
    public void testSequentialExecution() {
        Counter counter = new SeqCounterImpl();
        testCounter(counter, false);
    }

    @Test
    public void concurrentCounterTest() {
        Counter counter = new ConcurrentCounterImpl();
        testCounter(counter, true);

    }

    @Test
    public void mutexCounterTest() {
        Counter counter = new MutexCounterImpl();
        testCounter(counter, true);
    }

    @Test
    public void lockCounterTest() {
        Counter counter = new LockCounterImpl();
        testCounter(counter, true);
    }

    @Test
    public void magicCounterTest() {
        Counter counter = new MagicCounterImpl(threadNumber);
        testCounter(counter, true);
    }

    @Test
    public void magicArrayCounterTest() {
        Counter counter = new MagicArrayCounter(threadNumber);
        testCounter(counter, true);
    }

    private void testCounter(Counter counter, boolean assertTrue) {
        ExecutorService executors = Executors.newFixedThreadPool(threadNumber);

        List<Future> futures = range(0, incrementCallsCount)
                .mapToObj(i -> executors.submit(incrementRunnable(counter)))
                .collect(toList());

        for (Future future : futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        if (assertTrue) {
            assertEquals("Oops! Smth is wrong!", incrementCallsCount, counter.getValues());
            System.out.println(counter.getClass().getSimpleName() + ": "
                    + incrementCallsCount + " is equals " + counter.getValues());
        } else {
            assertNotEquals("Oops! Smth is wrong!", incrementCallsCount, counter.getValues());
            System.out.println(counter.getClass().getSimpleName() + ": "
                    + incrementCallsCount + " is not equals " + counter.getValues());
        }

        executors.shutdown();
    }

    private Runnable incrementRunnable(Counter counter) {
        return counter::increment;
    }
}