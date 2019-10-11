package HW.HW1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Array6 {

    private int pow;
    private int sum;
    private int size;


    public static void main(String[] args) throws IOException {
        new Array6().start();
    }

    public void start() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(br.readLine());
        String[] array = br.readLine().split(" ");
        pow = 0;
        sum = 0;

        while (true) {
            if ((Math.pow(2.0,pow)) > size) {
                break;
            }

            sum += Integer.parseInt(array[(int)Math.pow(2.0,pow) - 1]);
            pow++;
        }

        System.out.println(sum);
    }
}
