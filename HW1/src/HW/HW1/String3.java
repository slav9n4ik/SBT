package HW.HW1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class String3 {

    private ArrayList<String> words = new ArrayList<>();
    private int size;

    public static void main(String[] args) throws IOException {
        new String3().start();
    }

    public void start() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strings = br.readLine().split(",",-1);
        size = Integer.parseInt(br.readLine());

        for (int i = 0; i < strings.length; i++) {
            if (strings[i].length() >= size) {
                words.add(strings[i]);
            }
        }

        System.out.println(String.join(",", words));

    }
}
