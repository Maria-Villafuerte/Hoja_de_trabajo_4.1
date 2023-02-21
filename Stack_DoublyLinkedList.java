// Implementation of lists, using doubly linked elements.
// (c) 1998, 2001 duane a. bailey
import structure.DoublyLinkedList;

public class Stack_DoublyLinkedList<T> extends Stack{
    structure.DoublyLinkedList doublyLinkedList = new structure.DoublyLinkedList();
    @Override
    public int count() {
        return doublyLinkedList.size();
    }

    @Override
    public boolean isEmpty() {
        return doublyLinkedList.isEmpty();
    }

    @Override
    public void push(Object value) {
        doublyLinkedList.add(value);
    }

    @Override
    public Object pull() {
        Object x = doublyLinkedList.get();
        return doublyLinkedList.remove(x);
    }

    @Override
    public Object peek() {
        return doublyLinkedList;
    }
}