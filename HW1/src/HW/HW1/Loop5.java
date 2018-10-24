package HW.HW1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Loop5 {
    private int size;
    int[] result;

    public static void main(String[] args) throws IOException {
        new Loop5().start();
    }

    public void start() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        result = new int[5];

        for (int i = 0; i < size; i++) {
            result[Integer.parseInt(input[i])]++;
        }

        for (int i = 0; i < 5; i++) {
            if (result[i] != 0) {
                System.out.println(i + " " + result[i]);
            }
        }
    }
}
