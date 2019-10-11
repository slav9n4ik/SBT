package HW.HW1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Array5 {

    private int n;
    private int wCar;
    private int w;
    private int size;


    public static void main(String[] args) throws IOException {
        new Array5().start();
    }

    public void start() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        size = Integer.parseInt(input[0]);
        wCar = Integer.parseInt(input[1]);
        String[] array = br.readLine().split(" ");
        w = 0;

        for (int i = 0; i < size; i++) {
            if ((wCar - w) >= Integer.parseInt(array[i])) {
                n++;
                w += Integer.parseInt(array[i]);
            }
        }

        System.out.println(n + " " + w);
    }
}
