import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

public class FileStore implements Store<String> {
    @Override
    public void store(String s) {
        File file = new File("file.txt");
        try {
            Files.write(file.toPath(), s.getBytes());
        } catch (IOException e) {
            throw new StoreExeption("Can't store " + s + " to" + file, e);
        }
    }

    @Override
    public String get() {
        return null;
    }
}
