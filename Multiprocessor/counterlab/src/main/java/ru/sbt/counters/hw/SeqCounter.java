package ru.sbt.counters.hw;

import ru.sbt.counters.hw.Counter;

public class SeqCounter implements Counter {
    private long value;
    @Override
    public void increment() {
        value++;
    }

    @Override
    public long getValue() {
        return value;
    }
}
