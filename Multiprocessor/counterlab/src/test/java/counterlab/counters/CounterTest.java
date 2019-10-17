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

    @Test
    public void testSequentialExecution() {
        Counter counter = new SeqCounterImpl();
        int incrementCallsCount = 1418800;
        testCounter(counter, incrementCallsCount, false);
    }

    @Test
    public void concurrentCounterTest() {
        Counter counter = new ConcurrentCounterImpl();
        int incrementCallsCount = 1418800;
        testCounter(counter, incrementCallsCount, true);

    }

    @Test
    public void mutexCounterTest() {
        Counter counter = new MutexCounterImpl();
        int incrementCallsCount = 1418800;
        testCounter(counter, incrementCallsCount, true);
    }

    @Test
    public void lockCounterTest() {
        Counter counter = new LockCounterImpl();
        int incrementCallsCount = 1418800;
        testCounter(counter, incrementCallsCount, true);
    }

    @Test
    public void magicCounterTest() {
        Counter counter = new MagicCounterImpl(4);
        int incrementCallsCount = 1418800;
        testCounter(counter, incrementCallsCount, true);
    }

    private void testCounter(Counter counter, int incrementCallsCount, boolean assertTrue) {
        ExecutorService executors = Executors.newFixedThreadPool(4);

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
            assertEquals("Oops! Smth is wrong!", incrementCallsCount, counter.getValue());
            System.out.println(counter.getClass().getSimpleName() + ": "
                    + incrementCallsCount + " is equals " + counter.getValue());
        } else {
            assertNotEquals("Oops! Smth is wrong!", incrementCallsCount, counter.getValue());
            System.out.println(counter.getClass().getSimpleName() + ": "
                    + incrementCallsCount + " is not equals " + counter.getValue());
        }


        executors.shutdown();
    }

    private Runnable incrementRunnable(Counter counter) {
        return counter::increment;
    }
}