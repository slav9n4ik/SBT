
public class MyLinkedList<E> {

    private int size;
    private Node<E> first;
    private Node<E> last;

    public MyLinkedList() {
        this.size = 0;
        this.first = null;
        this.last = null;
    }


    public void add(E element) {
        if (size == 0) {
            first = new Node<>(null,element,null);
            last = first;
        } else {
            last = new Node(last, element, null);
            last.prev.next = last;
        }
        size++;
    }

    public void add(int index, E element) {
        if (index > size) {
            System.out.println("BoundExeption");
        } else {
            Node<E> pointer = first;
            Node<E> pointerNext;
            for (int i = 0; i < index; i++) {
                pointer = pointer.next;
            }
            pointerNext = pointer.next;
            pointer = new Node<>(pointer, element,pointerNext);
            pointer.prev.next = pointer;
            pointerNext.prev = pointer;
            size++;
        }
    }

    public E get(int index) {
        Node<E> pointer = first;
        if (index > size) {
            System.out.println("BoundExeption");
        } else {
            for (int i = 0; i < index; i++) {
                pointer = pointer.next;
            }
        }
        return pointer.next.item;
    }

    public void remove(int index) {
        Node<E> pointer = first;
        if (index > size) {
            System.out.println("BoundExeption");
        } else {
            for (int i = 0; i <= index; i++) {
                pointer = pointer.next;
            }

            pointer.prev.next = pointer.next;
            pointer.next.prev = pointer.prev;
            pointer.item = null;
            pointer.prev = null;
            pointer.next = null;
            size--;
        }
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}

