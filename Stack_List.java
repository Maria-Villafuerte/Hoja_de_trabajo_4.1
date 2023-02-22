import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Stack_List extends Stack{
    AbstractList list = new ArrayList();
    @Override
    public int count() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push(Object value) {
        list.add(value);
    }

    @Override
    public Object pull() {
        Object x = list.get(0);
        return list.remove(x);
    }

    @Override
    public Object peek() {
        return list.get(0);
    }
}
