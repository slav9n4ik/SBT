package HW.HW1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Loop4 {
    private int number;
    private double pow;
    private boolean stop;

    public static void main(String[] args) throws IOException {
        new Loop4().start();
    }

    public void start() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        number = Integer.parseInt(br.readLine());
        pow = 1.0;
        stop = false;

        while (!stop) {
            if ((number % Math.pow(2.0, pow)) == 0) {
                pow++;
            } else {
                stop = true;
            }
        }

        System.out.println((int) pow - 1);

    }
}
