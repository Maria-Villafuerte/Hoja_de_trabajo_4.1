
// An implementation of extensible arrays.
// (c) 1998, 2001 duane a. bailey
import structure.StackVector;
public class Stack_Vector<T> extends Stack{
    StackVector stackVector = new StackVector();
    @Override
    public int count() {
        return stackVector.size();
    }
    @Override
    public boolean isEmpty() {
        return stackVector.isEmpty();
    }

    @Override
    public void push(Object value) {
        stackVector.push(value);
    }

    @Override
    public Object pull() {
        return stackVector.get();
    }

    @Override
    public Object peek() {
        return stackVector.peek();
    }
}