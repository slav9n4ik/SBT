package ru.sbt.counters.hw.counters;

import ru.sbt.counters.hw.Counter;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicCounter implements Counter {

    private AtomicLong atomicLong = new AtomicLong();

    @Override
    public void increment() {
        atomicLong.addAndGet(1);
    }

    @Override
    public long getValue() {
        return atomicLong.get();
    }
}
