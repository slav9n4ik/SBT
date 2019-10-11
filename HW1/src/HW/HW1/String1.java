package HW.HW1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

    public class String1 {

    private int size;
    private int counter = 0;

    public static void main(String[] args) throws IOException {
        new String1().start();
    }

    public void start() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(br.readLine());
        Set<Character> vowels = new HashSet<Character>();
        for (char ch : "eyuioa".toCharArray()) {
            vowels.add(ch);
        }

        for (int i = 0; i < size; i++) {
            String line = br.readLine();
            StringBuilder sb = new StringBuilder(line);

            while ((sb.length() != 0) && (counter != 3)) {
                if (vowels.contains(Character.toLowerCase(sb.charAt(0)))) {
                    counter++;
                } else {
                    counter = 0;
                }
                sb.deleteCharAt(0);
            }

            if (counter != 3) {
                System.out.println(line);
            }

            counter = 0;
        }

    }
}
