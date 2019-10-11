package ex2;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThreadPool {
    private int threadNumber;
    private Runnable callback;
    private Runnable[] inputTasks;
    private AtomicInteger finishedThreads;
    private AtomicInteger startNumber;
    private AtomicInteger exceptionNumber;
    private AtomicInteger interruptedNumber;
    private volatile boolean interrupted;
    private volatile boolean isFinished;

    public MyThreadPool(int threadNumber, Runnable callback, Runnable[] tasks) {
        this.threadNumber = threadNumber;
        this.callback = callback;
        this.inputTasks = tasks;
        this.startNumber = new AtomicInteger(-1);
        this.finishedThreads = new AtomicInteger(0);
        this.interruptedNumber = new AtomicInteger(0);
        this.exceptionNumber = new AtomicInteger(0);
        this.interrupted = false;

        for (int i = 0; i < threadNumber; i++) {

            if (i == 3) {
                System.out.println("INTERRUPT");
                interrupt();
            }

            new Thread(()-> doRun(), "myThread: " + i).start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public synchronized int getCompletedTaskCount() {
        return finishedThreads.get();
    }

    public synchronized int getInterruptedTaskCount() {
        return interruptedNumber.get();
    }

    public synchronized int getFailedTaskCount() {
        return exceptionNumber.get();
    }

    public synchronized void interrupt() {
        interrupted = true;
        for (int i = 0; i < threadNumber - startNumber.get() - 1; i++) {
            interruptedNumber.incrementAndGet();
        }
    }

    public synchronized boolean isFinished() {
        return isFinished;
    }

    private void doRun() {
        try {
            if (!interrupted) {
                inputTasks[startNumber.incrementAndGet()].run();
                System.out.println("Finished");
                finishedThreads.incrementAndGet();
                if(finishedThreads.get() == 5) {
                    isFinished = true;
                    callback.run();
                }
            } else {
                if (finishedThreads.get() == 0) {
                    isFinished = true;
                }
            }
        } catch (Exception e) {
            System.out.println("Caught exception named:" + e.getMessage());
            exceptionNumber.incrementAndGet();
        }
    }
}
