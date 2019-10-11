package ex2;

public class Main {


    public static void main(String[] args) throws InterruptedException {
        new Main().start();
    }

    private void start() throws InterruptedException {
        Runnable[] tasks = new Task[5];
        Runnable callback = new Task("CallBack");

        ExecutionManager executionManager = new ExecutionManagerImpl();
        for (int i = 0; i < 5; i++) {
            tasks[i] = new Task("Task " + (i+1));
        }
        Context context = executionManager.execute(callback, tasks);
        Thread.sleep(1500);
        System.out.println("getCompletedTaskCount: "+context.getCompletedTaskCount());
        System.out.println("getFailedTaskCount: "+context.getFailedTaskCount());
        System.out.println("getInterruptedTaskCount: "+context.getInterruptedTaskCount());
    }
}
