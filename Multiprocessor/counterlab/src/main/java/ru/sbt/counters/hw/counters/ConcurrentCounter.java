package ru.sbt.counters.hw.counters;

import ru.sbt.counters.hw.Counter;

public class ConcurrentCounter implements Counter {

    private volatile long count;

    @Override
    public void increment() {
        //synchronized ()
    }

    @Override
    public long getValue() {
        return 2;
        //synchronized ()
    }
}
