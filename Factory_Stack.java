import structure.StackVector;
import java.util.ArrayList;

//Se encarga de preguntarle al usuario que tipo de stack le gustaría utilizar
public class Factory_Stack {
    public static <T> Stack getInstace (int TipoL){
        switch (TipoL){
            case 1: return new Stack_Vector();
            case 2: return new Stack_Arraylist();
            case 3: return new Stack_List();
            default: return  null;
        }
    }
}
