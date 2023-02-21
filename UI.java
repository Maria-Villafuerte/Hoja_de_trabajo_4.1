import jdk.dynalink.beans.StaticClass;

import java.util.Scanner;
import java.util.ArrayList;

public class UI {
    static Scanner sc = new Scanner(System.in);
    static Read_Archivo readFile = new Read_Archivo();
    static InfixToPostfix inf_post = new InfixToPostfix();

    public void calcular() {
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

                break;

            case 2:

                break;

            case 3:

                break;

            case 4:
                break;

            default:
                break;
        }

    }

}
