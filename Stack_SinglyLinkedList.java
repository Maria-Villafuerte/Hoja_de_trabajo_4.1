// Implementation of lists, using singly linked elements.
// (c) 1998, 2001 duane a. bailey

import structure.SinglyLinkedList;
public class Stack_SinglyLinkedList extends Stack{
    SinglyLinkedList stackSinglyLinkedList = new SinglyLinkedList();
    @Override
    public int count() {
        return stackSinglyLinkedList.size();
    }
    @Override
    public boolean isEmpty() {
        return stackSinglyLinkedList.isEmpty();
    }

    @Override
    public void push(Object value) {
        stackSinglyLinkedList.add(value);
    }

    @Override
    public Object pull() {
        Object x= stackSinglyLinkedList.get();
        return stackSinglyLinkedList.remove(x);
    }

    @Override
    public Object peek() {
        return stackSinglyLinkedList.getFirst();
    }


}