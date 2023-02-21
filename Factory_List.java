import structure.SinglyLinkedList;
import structure.DoublyLinkedList;
import structure.AbstractList;

public class Factory_List {
    public static <T> AbstractList getInstace (int TipoL){
        switch (TipoL){
            case 1: return new SinglyLinkedList();
            case 2: return new DoublyLinkedList();
            default: return  null;
        }
    }
}
