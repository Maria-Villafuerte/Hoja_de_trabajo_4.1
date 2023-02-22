/*
* Realizado en un ejercicio anterior
* Maria José Villafuerte
 * Fabiola Contreras
 * Madeline Castro
 * @category Ejercicio #2
 * Propósito: Realizaciones de pruebas jUnit para la calculadora
 * @date 30/01/2023
 */

* */

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Unit_testing {
    PostfixCalculator pc = new PostfixCalculator();
    Stack s = new Stack();

    Unit_testing(){
        s.push(1);
        s.push(3);
    }


    @Test
    void peek_Test(){
        assertEquals(3, s.peek());
    }

    @Test
    void count_Test(){
        assertEquals(2, s.count());
    }

    @Test
    void getItems_Test(){
        ArrayList<String> temp = new ArrayList<>();
        temp.add("1");temp.add("2");temp.add("+");temp.add("4");temp.add("*");temp.add("3");temp.add("+");

        assertEquals(temp, pc.getItems("1 2 + 4 * 3 + "));
    }

    @Test
    void isOneItem_Test(){
        assertEquals(false, pc.isOneItem(s));
    }

    @Test
    void Testing_calculate() {
        Calculadora calc = new Calculadora<>();
        int result = Calculadora.calculate("1 2 + 4 * 3 + ");

        assertEquals(15, result);
    }
}