package HW.HW1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class String2 {

    private int max = 0;
    private int counter = 0;

    public static void main(String[] args) throws IOException {
        new String2().start();
    }

    public void start() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder(br.readLine());

        for (int i = 0; i < sb.length(); i++) {
            if ((((int)(sb.charAt(i)) >= 65) && ((int)(sb.charAt(i)) <= 90))
                    || (((int)(sb.charAt(i)) >= 97) && ((int)(sb.charAt(i)) <= 122))) {
                counter++;
            } else {
                if (max < counter) {
                    max = counter;
                }

                counter = 0;
            }
        }

        System.out.println(max);
    }
}
