/*
* @author Alejandra Gudiel 19232
* @author Diego Crespo 19541
* @author Juan Pablo Pineda 19087
* Clase: 
*/

package main;

import java.util.Scanner;

//Clase para mostrar menu al usuario
public class Main {
	public static void main(final String args[]) {
        // Programcion defensiva
        int selectedOption = 0;

        // Mientras el usuario no eliga la opcion 2
        while (selectedOption != 2) {
            // Mostrar menu
            printMenu();
            try {
                // Nuevo Scanner
                final Scanner scannerInput = new Scanner(System.in);

                // Switch para los posibles casos
                switch (selectedOption) {
                    case 1:
                        // Pasar a metodo de operate
                        operate(scannerInput);
                        break;
                    case 2:
                        // Pasar a metodo de exitProgram
                        exitProgram(scannerInput);
                    default:
                        // Mensaje de error
                        throw new Exception("Invalid option");
                }
            } catch (final Exception e) {
                // Seguir desplegando mensaje hasta que ingrese opcion valida
                System.out.print("Enter your option:> " + e.getMessage());
                System.out.println("");
                System.out.println("");
            }
        }
    }

    // Metodo para mostrar menu principal al usuario
    private static void printMenu() {
        System.out.println("===================================");
        System.out.println("==============  Menu ==============");
        System.out.println("===================================");
        System.out.println("Select one of the next options ");
        System.out.println("	1. Enter operation");
        System.out.println("	2. Exit");
        System.out.println("");
        System.out.print("Enter your option:> ");
    }

    // Metodo para que usuario ingrese operacion en Lenguje Lisp
    private static void operate(final Scanner scannerInput) {
        // Desplegar mensajes
        System.out.println("");
        System.out.println("Enter operation, to exit this option enter 'exit'");

        // Mientras el usuario no ingese "exit"
        while (true) {
            // Mostrar mensaje
            System.out.print("lisp:> ");

            String lispOperation = "";
            while (scannerInput.hasNextLine()) {
                // Usuario ingresa operacion
                final String inputNext = scannerInput.nextLine();
                lispOperation += inputNext;

                // Si el usuario no ingresa nada "enter"
                if (inputNext.length() > 0) {
                    // volver a anterior while
                    break;
                }
            }

            // Si el usuario ingresa exit
            if (lispOperation.startsWith("exit")) {
                System.out.println("");
                System.out.println("");
                // Regresa a menu principal
                return;
            }

            try {
                // Tratar de hacer parse a Operacion ingresada
                final String result = ParserService.getInstance().parseMultipleOperation(lispOperation);
                // Desplegar cualquier resultado obtenido
                System.out.println("lisp:> " + result);
            } catch (final Exception e) {
                // mensaje de error
                System.out.println("lisp:> " + e.getMessage());
            }
        }
    }

    // Funcion para salir del programa
    private static void exitProgram(final Scanner scannerInput) {
    	//Cerrar scanner
        scannerInput.close();
        //Mensaje de despedida
        System.out.println("");
        System.out.println("Thanks for using this program");
        //Terminar todos los procesos
        System.exit(1);
    }
}
