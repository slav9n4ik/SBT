import java.io.*;

public class Main {

    public Main() throws FileNotFoundException {
    }

    public static void main(String[] args) throws IOException {
        new Main().start();
    }

    private void start() throws IOException {
        //System.out.println("Exercise1: " + new Exersice1().wordsNumberInText() + " words in text");
        //new Exersice2().differentWordsUpOrder();
        //new Exersice3().quantityOfWordsInText();
        //new Exersice4().inverseWords();
        new Exersice5().inverseWithIterator();


    }
}
