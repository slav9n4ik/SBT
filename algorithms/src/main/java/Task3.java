package main.java;

import java.util.ArrayList;
import java.util.List;

public class Task3 {

    private List<Integer> inputArray = new ArrayList<Integer>();
    private int sum = 0;

    public static void main(String[] args) {
        new Task3().start();
    }

    private void start() {

        int number = 5;

        for (int i = 0; i < 5; i++) {
            inputArray.add(i);
            sum += i;
        }

        boolean isNull = findDifference(number, 0);
        System.out.println(isNull);
    }

    public boolean findDifference(int number, int index) {

        if(number == 0)
            return true;

        if (index != (inputArray.size() - 1)) {
            index++;
            return findDifference(number, index) || findDifference(number - inputArray.get(index), index);
        } else {
            return false;
        }

    }
}
