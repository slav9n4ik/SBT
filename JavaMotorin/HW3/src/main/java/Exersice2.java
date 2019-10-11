import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Exersice2 implements ReadTextFromFile {
    private Reader file;
    private BufferedReader bufferedReader;
    private PriorityQueue<String> heap;

    public Exersice2() {
        super();
        heap = new PriorityQueue<String>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                Integer size1 = o1.length();
                Integer size2 = o2.length();
                return size2.compareTo(size1);
            }
        });
    }

    public void differentWordsUpOrder() throws IOException {
        readTextFromFile();
        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            String[] arr = line.split(" ");
            for (int i = 0; i < arr.length; i++)
                heap.add(arr[i]);
        }
        System.out.println("Exercise2: " );
        while (!heap.isEmpty())
            System.out.println(heap.poll());
        file.close();
    }

    @Override
    public void readTextFromFile() throws FileNotFoundException {
        this.file = new InputStreamReader(new FileInputStream("input.txt"));
        this.bufferedReader = new BufferedReader(file);
    }
}
