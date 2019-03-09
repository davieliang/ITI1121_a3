import java.util.ArrayList;
import java.util.NoSuchElementException;

public class QueueImplementation<E> implements Queue<E> {

    private ArrayList<E> array;

    public QueueImplementation() {
        array = new ArrayList<E>();
    }

    public boolean isEmpty() {
        return array.isEmpty();
    }

    public void enqueue(E elem) {
    	if (elem != null)
        	array.add(elem);
    }

    public E dequeue() {
    	if (array.isEmpty())
    		throw new NoSuchElementException();
        return array.remove(0);
    }

}
