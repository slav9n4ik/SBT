import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Exersice4 implements ReadTextFromFile {

    private Reader file;
    private BufferedReader bufferedReader;
    private ArrayList<String> words;

    public Exersice4() {
        super();
        words = new ArrayList<>();
    }

    public void inverseWords() throws IOException {
        readTextFromFile();
        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            String[] arr = line.split(" ");
            for (int i = 0; i < arr.length; i++)
                words.add(arr[i]);
        }
        while (!words.isEmpty()) {
            System.out.println(words.get(words.size()-1));
            words.remove(words.size() - 1);
        }
        file.close();
    }

    @Override
    public void readTextFromFile() throws FileNotFoundException {
        this.file = new InputStreamReader(new FileInputStream("input.txt"));
        this.bufferedReader = new BufferedReader(file);
    }
}
