package counterlab.lock;

public class BakeryLock {

    volatile boolean[] flag;
    volatile int[] label;

    public BakeryLock(int threadNumber) {
        flag = new boolean[threadNumber * 100];
        label = new int[threadNumber * 100];
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
            if (k != id) {
                while (flag[k]) {}
                while (label[k] != 0 && (label[id] > label[k] || (label[id] == label[k] && id > k))) {}
            }
        }
    }

    public void unlock() {
        int id = getCurrentThreadIntID();
        label[id] = 0;
    }

    private int getCurrentThreadIntID() {
        return Long.hashCode(Thread.currentThread().getId()) % (label.length * 100);
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
