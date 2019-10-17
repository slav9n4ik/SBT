package counterlab;

import java.util.*;

public class BakeryHashLock {
    private final int threadNumber;
    private volatile Map<Long, Boolean> flagMap;
    private volatile Map<Long, Integer> labelMap;

    public BakeryHashLock(int threadNumber) {
        this.threadNumber = threadNumber;
        flagMap = new HashMap<>(threadNumber);
        labelMap = new HashMap<>(threadNumber);
    }

    public void lock() {
        long id = getCurrentThreadIntID();
        flagMap.put(id, true);
        labelMap.put(id, max(labelMap) + 1);
        flagMap.put(id, false);
        for (long k = 0; k < flagMap.size(); k++) {
            while (flagMap.get(k)) {
            }
            while (labelMap.get(k) != 0 && (labelMap.get(id) > labelMap.get(k) || (labelMap.get(id).equals(labelMap.get(k)) && id > k))) {
            }
        }
    }

    public void unlock() {
        long id = getCurrentThreadIntID();
        labelMap.put(id, 0);
    }

    private long getCurrentThreadIntID() {
        return Thread.currentThread().getId();
    }

    private int max(Map<Long, Integer> map) {
        if (map.size() != 0) {
            Optional<Map.Entry<Long, Integer>> maxEntry = map.entrySet()
                    .stream()
                    .max(Comparator.comparing(Map.Entry::getValue)
                    );
            return maxEntry.get().getValue();
        }
        return 0;
    }
}

