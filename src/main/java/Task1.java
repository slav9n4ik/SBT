package main.java;

import java.util.ArrayList;
import java.util.List;

public class Task1 {

    private List<Integer> inputArray = new ArrayList<Integer>();
    private int sum = 0;
    //private int index = 0;

    public static void main(String[] args) {
        new Task1().start();
    }

    private void start() {
        for (int i = 0; i < 4; i++) {
            inputArray.add(i);
            sum += i;
        }

        int min = findDifference(inputArray.get(0), 0);
        System.out.println(min);
    }

    public int findDifference(int sumOfLeftHeap, int index) {

        if (index != (inputArray.size() - 1)) {
            index++;
            return Math.min(findDifference(sumOfLeftHeap, index), findDifference(sumOfLeftHeap + inputArray.get(index), index));
        } else {
            return Math.abs(sum - 2*sumOfLeftHeap);
        }

    }
}
