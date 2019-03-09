public interface Queue<E> {

    public boolean isEmpty();
    public void enqueue(E elem);
    public E dequeue();

}
