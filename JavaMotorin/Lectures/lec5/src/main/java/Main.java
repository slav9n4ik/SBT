import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        new Main().start();
    }

    private void start() {
        MyLinkedList<Integer> iList = new MyLinkedList<>();

        for (int i = 0; i < 10; i++) {
            iList.add(i*i);
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(iList.get(i*i));
        }
    }
}
