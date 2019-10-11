package HW.HW1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Loop8 {
    private int years;

    public static void main(String[] args) throws IOException {
        new Loop8().start();
    }

    public void start() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        years = Integer.parseInt(br.readLine());

        if (years < 7) {
            System.out.println("preschool child");
        }

        if (years >= 7 && years <= 17) {
            System.out.println("schoolchild " + (years - 6));
        }

        if (years >= 18 && years <= 22) {
            System.out.println("student " + (years - 17));
        }

        if (years >= 23) {
            System.out.println("specialist");
        }
    }
}

