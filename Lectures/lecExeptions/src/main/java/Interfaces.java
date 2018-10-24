import java.io.FileNotFoundException;
import java.io.IOException;

interface Interfaces1 {
    void run() throws IOException;
}

interface Interfaces2 {
    void run() throws FileNotFoundException;
}

interface Interfaces3 {
    void run() throws Exception;
}

class Impl implements Interfaces1,Interfaces2, Interfaces3 {

    @Override
    public void run() throws FileNotFoundException {

    }
}