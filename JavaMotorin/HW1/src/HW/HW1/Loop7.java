package HW.HW1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Loop7 {
    private int sub;

    public static void main(String[] args) throws IOException {
        new Loop7().start();
    }

    public void start() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] numbers = br.readLine().split(" ");
        int a = Integer.parseInt(numbers[0]);
        int b = Integer.parseInt(numbers[1]);
        sub  = 0;

        while ((a !=0) && (b != 0)) {
            if (a > b) {
                a = a - b;
            } else {
                b = b - a;
            }
            sub++;
        }

        System.out.println(sub + " " + a);
    }
}
