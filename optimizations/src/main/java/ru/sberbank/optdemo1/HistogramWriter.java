package ru.sberbank.optdemo1;

import org.HdrHistogram.AtomicHistogram;
import org.HdrHistogram.Histogram;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

@Component
public class HistogramWriter {
    private final Histogram histogram;

    public HistogramWriter() {
        this.histogram = new AtomicHistogram(TimeUnit.MINUTES.toNanos(5), 3);
    }

    public AutoCloseable wrap() {
        return wrap(histogram);
    }

    public void emit(File resultsFile) {
        try {
            emit(resultsFile, histogram);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private AutoCloseable wrap(Histogram h) {
        return new AutoCloseable() {
            long start = System.nanoTime();
            @Override
            public void close() {
                long end = System.nanoTime();
                h.recordValue(end - start);
            }
        };
    }

    private void emit(File resultsFile, Histogram results) throws IOException {
        try (FileOutputStream fout = new FileOutputStream(resultsFile)) {
            results.outputPercentileDistribution(new PrintStream(fout), 1000000.0);
        }
    }
}

