package ex2;

public class ExecutionManagerImpl implements ExecutionManager {

    @Override
    public Context execute(Runnable callback, Runnable... tasks) {

        MyThreadPool myThreadPool = new MyThreadPool(5, callback, tasks);
        Context context = new Context() {
            @Override
            public int getCompletedTaskCount() {
                return myThreadPool.getCompletedTaskCount();
            }

            @Override
            public int getFailedTaskCount() {
                return myThreadPool.getFailedTaskCount();
            }

            @Override
            public int getInterruptedTaskCount() {
                return myThreadPool.getInterruptedTaskCount();
            }

            @Override
            public void interrupt() {
                myThreadPool.interrupt();
            }

            @Override
            public boolean isFinished() {
                return false;
            }
        };
        return context;
    }
}
