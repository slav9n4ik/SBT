package counterlab.lock;

public class BakeryLock {
    final int threadNumber;
    volatile boolean[] flag;
    volatile int[] label;

    public BakeryLock(int threadNumber) {
        this.threadNumber = threadNumber;
        flag = new boolean[threadNumber];
        label = new int[threadNumber];
        for (int i = 0; i < threadNumber; i++) {
            flag[i] = false;
            label[i] = 0;
        }
    }

    public void lock() {
        int id = getCurrentThreadIntID();
        flag[id] = true;
        label[id] = max(label) + 1;
        flag[id] = false;
        for (int k = 0; k < flag.length; k++) {
            while (flag[k]) {
            }
            while (label[k] != 0 && (label[id] > label[k] || (label[id] == label[k] && id > k))) {
            }
        }
    }

    public void unlock() {
        int id = getCurrentThreadIntID();
        label[id] = 0;
    }

    private int getCurrentThreadIntID() {
        //Неочень решение
        String id = Thread.currentThread().getName().split("-")[3];
        return Integer.parseInt(id) - 1;
    }

    private int max(int[] array) {
        int max = 0;
        for (int i : array) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }
}
