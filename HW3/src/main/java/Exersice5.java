import java.io.*;
import java.util.ArrayList;

public class Exersice5 implements ReadTextFromFile{
    private Reader file;
    private BufferedReader bufferedReader;
    private ArrayList<String> words;

    public Exersice5() {
        super();
        words = new ArrayList<>();
    }



    public void inverseWithIterator() throws IOException {
        readTextFromFile();
        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            String[] arr = line.split(" ");
            for (int i = 0; i < arr.length; i++)
                words.add(arr[i]);
        }
        MyArrayInvertStringsInText ais = new MyArrayInvertStringsInText(words);
        while (ais.hasNext()) {
            System.out.println(ais.next());
        }
        file.close();
    }

    @Override
    public void readTextFromFile() throws FileNotFoundException {
        this.file = new InputStreamReader(new FileInputStream("input.txt"));
        this.bufferedReader = new BufferedReader(file);
    }
}
