package ex2;

public class Task implements Runnable {

    private String name;
    static int count;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + " start");
        count++;
        if (count == 3) throw new RuntimeException("Exception in task " + name);
        try {
            Thread.sleep(2000+count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
