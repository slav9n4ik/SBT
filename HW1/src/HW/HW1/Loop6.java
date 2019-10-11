package HW.HW1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Loop6 {
    private int number;
    private int ft;
    private int dm;

    public static void main(String[] args) throws IOException {
        new Loop6().start();
    }

    public void start() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        number = Integer.parseInt(br.readLine());

        ft = (int) Math.floor(number/36);
        dm = (int) Math.round((number%36)/3.0);

        System.out.println(ft + " " + dm);

    }
}
