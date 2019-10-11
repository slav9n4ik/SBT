package HW.HW1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Array1 {

    private int max = 0;
    private int size;
    private int[] array;

    public static void main(String[] args) throws IOException {
        new Array1().start();
    }

    public void start() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(br.readLine());
        String[] numbers = br.readLine().split(" ");
        array = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = Integer.parseInt(numbers[i]);
        }
        findMax();
        changeMax();
        max = 0;
        findMax();
        changeMax();

        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
        }
    }

    public void findMax() {
        for (int i = 0; i < size; i++) {
            if (max < array[i]) {
                max = array[i];
            }
        }
    }

    public void changeMax() {
        for (int i = 0; i < size; i++) {
            if (array[i] == max) {
                array[i] = (int) Math.floor(max/2.0);
            }
        }
    }
}
