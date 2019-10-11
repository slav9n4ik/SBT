package HW.HW1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Array4 {

    private int size;
    private int min;
    private int count;

    public static void main(String[] args) throws IOException {
        new Array4().start();
    }

    public void start() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(br.readLine());
        String[] numbers = br.readLine().split(" ");
        count = Integer.parseInt(br.readLine());

        for (int i = 0; i < count; i++) {
            String[] request = br.readLine().split(" ");
            int l = Integer.parseInt(request[0]);
            int r = Integer.parseInt(request[1]);
            min = Integer.parseInt(numbers[l -1]);

            for (int j = l - 1; j < r; j++) {
                if (Integer.parseInt(numbers[j]) < min) {
                    min = Integer.parseInt(numbers[j]);
                }
            }
            System.out.println(min);
        }
    }
}
