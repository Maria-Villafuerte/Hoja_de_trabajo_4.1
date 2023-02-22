import jdk.dynalink.beans.StaticClass;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * @author
 * Maria José Villafuerte
 * @category Ejercicio #4
 * Propósito: Ser la interfaz del usuario, mostrará la operación a realizar y el resultado, dichas operaciones pueden ser hechas con diferentes tipos de stacks y listas
 * @date 21/02/2023
 */

public class UI {
    static Scanner sc = new Scanner(System.in);
    static Read_Archivo readFile = new Read_Archivo();
    static InfixToPostfix inf_post = new InfixToPostfix();

    public static void calcular() {
        System.out.println("\nBienvenido a la Calculadora Postfix");
        ArrayList<String> postfix = readFile._readfile("Datos.txt//");
        ArrayList<String> prueba = inf_post.traslate(postfix);
        Calculadora calculadora = new Calculadora();

        for (String element: prueba) {
            System.out.println("\n\tOperación a realizar: " + element);

            System.out.println("\tResultado obtenido: " + Calculadora.calculate(element) + "\n");
        }
        System.out.println("\nSe han hecho los cálculos de todo el archivo");
    }

        public static void main(String[] args) {
        System.out.println("\nBienvenido a la calculadora");
        ArrayList<String> postfix = Read_Archivo._readfile("Datos.txt//");
        System.out.println("\n");
        System.out.println("¿Qué estructura de datos desea utilizar?");
        System.out.println("\t1. Arraylist");
        System.out.println("\t2. Doble Linked IList");
        System.out.println("\t3. Single Linked IList");
        System.out.println("\t4. Vector");
        int type = sc.nextInt();
        System.out.println("\n");
        switch (type) {
            case 1:
                Factory_Stack.getInstace(2);
                calcular();
                break;

            case 2:
                Factory_Stack.getInstace(3);
                Factory_List.getInstace(2);
                calcular();
                break;

            case 3:
                Factory_Stack.getInstace(3);
                Factory_List.getInstace(1);
                calcular();
                break;

            case 4:
                Factory_Stack.getInstace(1);
                calcular();
                break;

            default:
                break;
        }

    }

}
