import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderTest {
    public static void main(String[] args) throws IOException {
        File file = new File("/home/viacheslav/Documents/JavaSBT/Java/lecExeptions/src/main/java/Main.java");

        System.out.println("1");
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (true) {
                String s = reader.readLine();
                if (s == null) break;
                System.out.println(s);
            }
            Integer z = null;
            System.out.println(z + 2);
            System.out.println("2");
        }
//        } finally {
//            System.out.println("3");
//            reader.close();
//            Integer.parseInt("sdfsdf");
//            System.out.println("4");
//        }

        System.out.println("5");

    }
}
