import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayInvertStringsInText implements Iterator<String>, Iterable<String>{

    ArrayList<String> arr;
    private int index;


    public MyArrayInvertStringsInText(ArrayList<String> arr) {
        this.arr = arr;
        index = arr.size() - 1;
    }

    @Override
    public Iterator<String> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        if (this.index >= 0) return true;
        //this.index = arr.size() - 1;
        return false;
    }

    @Override
    public String next() {
        if (this.index < 0) {
            this.index = arr.size() - 1;
            throw new NoSuchElementException();
        }
        this.index--;
        return this.arr.get(this.index+1);
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}
