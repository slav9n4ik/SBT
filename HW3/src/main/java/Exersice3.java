import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Exersice3 implements ReadTextFromFile{

    private Reader file;
    private BufferedReader bufferedReader;
    HashMap<String,Integer> map = new HashMap<>();

    public void quantityOfWordsInText() throws IOException {
        readTextFromFile();
        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            String[] arr = line.split(" ");
            for (String s:arr) {
                if (map.containsKey(s)) {
                    int value = map.get(s);
                    map.put(s,++value);
                } else {
                    map.put(s,1);
                }
            }
        }
        for (Map.Entry<String, Integer> pair: map.entrySet()) {
            System.out.println(pair.getKey() + " : " + pair.getValue());
        }
        file.close();
    }

    @Override
    public void readTextFromFile() throws FileNotFoundException {
        this.file = new InputStreamReader(new FileInputStream("input.txt"));
        this.bufferedReader = new BufferedReader(file);
    }
}

