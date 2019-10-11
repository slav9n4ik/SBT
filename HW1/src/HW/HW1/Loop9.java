package HW.HW1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Loop9 {
    private int size;
    private int count;

    public static void main(String[] args) throws IOException {
        new Loop9().start();
    }

    public void start() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(br.readLine());
        String[] array = br.readLine().split(" ");
        int min = Integer.parseInt(array[0]);
        count = 1;

        for (int i = 1; i < size; i++) {
            if (min > Integer.parseInt(array[i])) {
                count = 0;
                min = Integer.parseInt(array[i]);
            }

            if (min == Integer.parseInt(array[i])) {
                count++;
            }
        }

        System.out.println(count);
    }
}

