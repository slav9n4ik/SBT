package HW.HW1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class String4 {

    private int words;
    private boolean flag;

    public static void main(String[] args) throws IOException {
        new String4().start();
    }

    public void start() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder(br.readLine());
        words = 0;
        flag = false;
        int i = 0;

        while (i < sb.length()) {
            if ((((int)(sb.charAt(i)) >= 65) && ((int)(sb.charAt(i)) <= 90))
                    || (((int)(sb.charAt(i)) >= 97) && ((int)(sb.charAt(i)) <= 122))) {
                flag = true;
            } else {
                if (flag) {
                    words++;
                }
                flag = false;
            }
            i++;
        }

        if (flag) {
            System.out.println(words + 1);
        } else {
            System.out.println(words);
        }
    }
}