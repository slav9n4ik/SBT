package counterlab.benchmarks;

import counterlab.counters.*;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@Fork(value = 1)
@Warmup(iterations = 1)
@Measurement(iterations = 3)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class CountersBenchmark {

    private static final int THREADS_NUMBER = 4;

    @State(Scope.Benchmark)
    public static class CountersState {
        Counter concurrentCounter = new ConcurrentCounterImpl();
        Counter lockCounter = new LockCounterImpl();
        Counter magicCounter = new MagicCounterImpl(THREADS_NUMBER);
        Counter mutexCounter = new MutexCounterImpl();
        Counter seqCounter = new SeqCounterImpl();
    }


    @Benchmark
    @Group("ConcurrentCounter")
    @GroupThreads(THREADS_NUMBER)
    public void concurrentIncCounterTest(CountersState state) {
        state.concurrentCounter.increment();
    }

    @Benchmark
    @Group("ConcurrentCounter")
    @GroupThreads(THREADS_NUMBER)
    public void concurrentGetCounterTest(CountersState state) {
        state.concurrentCounter.getValue();
    }

    @Benchmark
    @Group("LockCounter")
    @GroupThreads(THREADS_NUMBER)
    public void lockIncCounterTest(CountersState state) {
        state.lockCounter.increment();
    }

    @Benchmark
    @Group("LockCounter")
    @GroupThreads(THREADS_NUMBER)
    public void lockGetCounterTest(CountersState state) {
        state.lockCounter.getValue();
    }

    @Benchmark
    @Group("MagicCounter")
    @GroupThreads(THREADS_NUMBER)
    public void magicIncCounterTest(CountersState state) {
        state.magicCounter.increment();
    }

    @Benchmark
    @Group("MagicCounter")
    @GroupThreads(THREADS_NUMBER)
    public void magicGetCounterTest(CountersState state) {
        state.magicCounter.getValue();
    }

    @Benchmark
    @Group("MutexCounter")
    @GroupThreads(THREADS_NUMBER)
    public void mutexIncCounterTest(CountersState state) {
        state.mutexCounter.increment();
    }

    @Benchmark
    @Group("MutexCounter")
    @GroupThreads(THREADS_NUMBER)
    public void mutexGetCounterTest(CountersState state) {
        state.mutexCounter.getValue();
    }

    @Benchmark
    @Group("SeqCounter")
    @GroupThreads(THREADS_NUMBER)
    public void seqIncCounterTest(CountersState state) {
        state.seqCounter.increment();
    }

    @Benchmark
    @Group("SeqCounter")
    @GroupThreads(THREADS_NUMBER)
    public void seqGetCounterTest(CountersState state) {
        state.seqCounter.getValue();
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(CountersBenchmark.class.getName())
                .forks(1)
                .build();

        new Runner(options).run();
    }

}
