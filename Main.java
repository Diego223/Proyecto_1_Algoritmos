 /*
* Universidad del Valle de Guatemala
* Algoritmos y estructuras de datos
* Proyecto 1
* @author Diego Crespo 19541
* @author Alejandra GUdiel 19232
* @author Juan Pablo Pineda 19087
* Clase encargada de: mostrar la interfaz al usuario para usar lISP
*/

package main;

import main.CreateParser;
import java.util.Scanner;


public class Main {
	public static void main(String args[]) {
		
		int selectedOption = 0;

		//comienza a  recorrer el menu
        while (selectedOption != 2) {
            printMenu();

            //progra defensiva
            try {
                Scanner scannerInput = new Scanner(System.in);

                try {
                	//se parsea la opcion ingrsada
                    selectedOption = Integer.parseInt(scannerInput.nextLine());
                } catch (Exception e) {
                    throw new Exception("Sólo se permiten números :/ ");
                }

                //switch para las opciones
                switch (selectedOption) {
                    case 1:
                    	//ingresar una operacion
                        operate(scannerInput);
                        break;
                    case 2:
                    	//salir del progama
                        exitProgram(scannerInput);
                    default:
                        throw new Exception("Opción inválida :( ");
                }
            } catch (Exception e) {
            	//Seguir desplegando mensaje hasta que ingrese opcion valida
                System.out.print("Ingrese una opcion >> " + e.getMessage());
                System.out.println("");
                System.out.println("");
            }
        }
    }

	//se despliega el menú principal
    private static void printMenu() {
        System.out.println("======================================================================");
        System.out.println("===================  Bienvenido al sistema LISP ======================");
        System.out.println("======================================================================");
        System.out.println("Seleccione una opción: ");
        System.out.println("	1. Ingresar operación ");
        System.out.println("	2. Exit");
        System.out.println("");
        System.out.print("Ingrese una opcion >> ");
    }

    //método para ingresar la operacion
    private static void operate(Scanner scannerInput) {
        System.out.println("");
        System.out.println("Ingrese una operación \n Si desea salir escriba: exit ");

        //ciclo while para ejecutar las operaciones hasta que el usuario no lo desee
        while (true) {
            System.out.print("LISP >> ");

            String lispOperation = "";
            while (scannerInput.hasNextLine()) {
                String inputNext = scannerInput.nextLine();
                lispOperation += inputNext;

                if (inputNext.length() > 0) {
                	//volver a anterior while
                    break;
                }
            }

            //ciclo if para salir del sistema
            if (lispOperation.startsWith("exit")) {
                System.out.println("");
                System.out.println("");
                
                return;
            }

            //progra defensiva
            try {
            	//se hace parse para realizar la función
                String result = CreateParser.getInstance().parseMultipleOperation(lispOperation);
                //desplegar el resultado obtenido
                System.out.println("LISP >> " + result);
            } catch (Exception e) {
                System.out.println("LISP >> " + e.getMessage());
            }
        }
    }

    //funcón para salir del programa
    private static void exitProgram(Scanner scannerInput) {
    	
        scannerInput.close();
        System.out.println("");
        System.out.println("Regrese pronto :) ");
        System.exit(1);
    }
}
