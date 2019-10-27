package counterlab.counters;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.*;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CounterTest {

    private static final int threadNumber = 240;
    private static final int incrementCallsCount = 5_500_000;

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

    @Test
    public void magicArrayCounter2Test() {
        CountDownLatch countDownLatch = new CountDownLatch(threadNumber);
        MagicArray2Counter counter = new MagicArray2Counter(threadNumber, countDownLatch);
        testForMagicArray2(counter, countDownLatch);
    }

    private void testForMagicArray2(MagicArray2Counter counter, CountDownLatch countDownLatch) {
        ExecutorService executors = Executors.newFixedThreadPool(threadNumber);


        for (int i = 0; i < threadNumber; i++) {
            executors.execute(counter::initInCounter);
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Total: " + counter.getValue().size());

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

        assertEquals("Oops! Smth is wrong!", incrementCallsCount, counter.getValues());
        System.out.println(counter.getClass().getSimpleName() + ": "
                + incrementCallsCount + " is equals " + counter.getValues());
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