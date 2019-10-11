package ex1;

public class Main {

    public static void main(String[] args) {
        new Main().start();
    }

    private void start() {
        Task<String> task = new Task<>(() -> {
            Thread.sleep(2000);
            return Thread.currentThread().getName();
        });

        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(() -> doRun(task), "myThread_" + i);
            thread.start();
        }
    }

    private static void doRun(Task<String> task) {
        try {
            String result = task.get();
            System.out.println("Result of " + Thread.currentThread().getName()+ ": " + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
