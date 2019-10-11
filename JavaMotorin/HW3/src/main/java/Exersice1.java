import java.io.*;

public class Exersice1 implements ReadTextFromFile{

    private Reader file;
    private BufferedReader bufferedReader;
    private int number;

    public int wordsNumberInText() throws IOException {
        readTextFromFile();
        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            String[] arr = line.split(" ");
            number += arr.length;
        }
        file.close();
        return number;
    }

    @Override
    public void readTextFromFile() throws FileNotFoundException {
        this.file = new InputStreamReader(new FileInputStream("input.txt"));
        this.bufferedReader = new BufferedReader(file);
    }
}
