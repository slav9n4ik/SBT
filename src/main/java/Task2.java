package main.java;

import java.util.ArrayList;
import java.util.List;

public class Task2 {

    private List<Integer> inputArray = new ArrayList<Integer>();
    private int sum = 0;

    public static void main(String[] args) {
        new Task2().start();
    }

    private void start() {
        for (int i = 0; i < 3; i++) {
            inputArray.add(i);
            sum += i;
        }

        boolean isNull = findDifference(inputArray.get(0), 0);
        System.out.println(isNull);
    }

    public boolean findDifference(int sumOfLeftHeap, int index) {

        if (index != (inputArray.size() - 1)) {
            index++;
            return findDifference(sumOfLeftHeap, index) || findDifference(sumOfLeftHeap + inputArray.get(index), index);
        } else {
            return Math.abs(sum - 2*sumOfLeftHeap) == 0;
        }

    }
}
