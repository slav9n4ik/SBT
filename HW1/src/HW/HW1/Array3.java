package HW.HW1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Array3 {

    private int size;
    private int middle;
    private int counter;

    public static void main(String[] args) throws IOException {
        new Array3().start();
    }

    public void start() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(br.readLine());
        String[] numbers = br.readLine().split(" ");

        if (size == 1) {
            counter = 0;
        } else {
            if (size%2 == 0) {
                middle = size /2;
            } else {
                middle = (int) Math.floor(size /2.0);
            }

            for (int i = 0; i < middle; i++) {
                if (Integer.parseInt(numbers[i]) != (Integer.parseInt(numbers[size - i - 1]))) {
                    counter++;
                }
            }
        }

        System.out.println(counter);
    }

}
