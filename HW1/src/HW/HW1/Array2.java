package HW.HW1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Array2 {

    private boolean flag = false;
    private int sizeA;
    private ArrayList<Integer> arrayA;
    private int sizeB;
    private ArrayList<Integer> arrayB;

    public static void main(String[] args) throws IOException {
        new Array2().start();
    }

    public void start() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sizeA = Integer.parseInt(br.readLine());
        String[] numbersA = br.readLine().split(" ");
        sizeB = Integer.parseInt(br.readLine());
        String[] numbersB = br.readLine().split(" ");
        arrayA = new ArrayList<>();
        arrayB = new ArrayList<>();

        for (int i = 0; i < Math.max(sizeA,sizeB); i++) {
            if (i < sizeA) {
                arrayA.add(Integer.parseInt(numbersA[i]));
            }
            if (i < sizeB) {
                arrayB.add(Integer.parseInt(numbersB[i]));
            }
        }

        int i = 0;
        while ((arrayA.size() != 0) && (i < arrayA.size())) {
            int k = 0;
            flag = false;
            while ((arrayB.size() != 0) && (k < arrayB.size())) {
                if (arrayA.get(i).equals(arrayB.get(k))) {
                    arrayB.remove(k);
                    flag = true;
                    break;
                } else {
                    k++;
                }
            }

            if (!flag) {
                arrayA.remove(i);
            } else {
                i++;
            }

        }

        if (arrayA.size() == 0) {
            System.out.println(0);
        } else {
            System.out.println(arrayA.size());
            for (int j : arrayA) {
                System.out.print(j + " ");
            }
        }
    }
}
