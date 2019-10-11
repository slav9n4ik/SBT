package HW.HW1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Loop3 {

    private int size;
    private int min;
    private int index;

    public static void main(String[] args) throws IOException {
        new Loop3().start();
    }

    public void start() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(br.readLine());
        String[] numbers = br.readLine().split(" ");
        min = Integer.parseInt(numbers[0]);
        index = 1;

        for (int i = 1; i < size; i++) {
            if (Integer.parseInt(numbers[i]) < min) {
                min = Integer.parseInt(numbers[i]);
                index = i + 1;
            }
        }

        System.out.println(index);
    }
}
