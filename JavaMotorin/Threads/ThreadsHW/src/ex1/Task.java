package ex1;

import java.util.concurrent.Callable;

public class Task<T> {

    private Callable<? extends T> callable;
    private boolean isFirst;
    private T excutionResult;


    public Task(Callable<? extends T> callable) {
        this.callable = callable;
        isFirst = true;
    }

    public T get() throws InterruptedException {
        synchronized (this) {
            if (isFirst) {
                try {
                    System.out.println(Thread.currentThread().getName() + " came first");
                    excutionResult = callable.call();
                    isFirst = false;
                } catch (Exception e) {
                    throw new MyCallException("Exception in call()");
                }
            }
        }

        return excutionResult;
    }
}
