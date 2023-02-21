import java.util.ArrayList;

public class Stack_Arraylist extends Stack {
    ArrayList newArray = new ArrayList<>();
    @Override
    public int count() {

        return newArray.size();
    }
    @Override
    public boolean isEmpty() {

        return newArray.isEmpty();
    }

    @Override
    public void push(Object value) {

        newArray.add(0,value);
    }

    @Override
    public Object pull() {
        return newArray.remove(0);
    }

    @Override
    public Object peek() {
        return newArray.get(0);
    }

}
