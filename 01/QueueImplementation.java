import java.util.ArrayList;

public class QueueImplementation<E> implements Queue<E> {

    private ArrayList<E> array;

    public QueueImplementation() {
        array = new ArrayList<E>();
    }

    public boolean isEmpty() {
        return array.isEmpty();
    }

    public void enqueue(E elem) {
        array.add(elem);
    }

    public E dequeue() {
        return array.remove(0);
    }

}
