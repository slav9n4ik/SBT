package HW.HW1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Loop1 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        String[] numbers = br.readLine().split(" ");

        int sum = Integer.parseInt(numbers[0]);

        for (int i = 1; i < size; i++) {
            if (i%2 != 0) {
                sum -= Integer.parseInt(numbers[i]);
            } else {
                sum += Integer.parseInt(numbers[i]);
            }
        }

        System.out.println(sum);
    }
}
