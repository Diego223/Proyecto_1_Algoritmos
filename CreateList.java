/*
* Universidad del Valle de Guatemala
* Algoritmos y estructuras de datos
* Proyecto 1
* @author Diego Crespo 19541
* @author Alejandra GUdiel 19232
* @author Juan Pablo Pineda 19087
* Clase encargada de: crear las listas en lisp
*/

package main;

import main.CreateParser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class CreateList {
	private static List<String> list;
    private static CreateList createList;

    private CreateList() {
    }

    //se cra una nueva instancia
    public static CreateList getInstance() {
        if (createList == null) {
            list = new ArrayList<>();
            createList = new CreateList();
        }
        return createList;
    }
    
    //operar listas
    public String operateListLisp(String lispOperation) throws Exception {
    
        int leftParenthesisIndex = lispOperation.indexOf("(");
        int rightParenthesisIndex = lispOperation.indexOf(")");

        if (leftParenthesisIndex < 0 || rightParenthesisIndex < 0) {
            throw new Exception("Operación inválida");
        }

        //separar los componentes del array
        String setQOperation = lispOperation.substring(leftParenthesisIndex + 1, rightParenthesisIndex).trim();
        String[] operationComponents = setQOperation.split(" ");

        //ingresar valores a la lista
        List<String> listComponents = new ArrayList<>(Arrays.asList(operationComponents));
        listComponents.remove(0);
        
        //parsear los valores
        for (String component : listComponents) {
            CreateParser.getInstance().parseParameter(component);
        }
        
        //añadir componentes a la lista
        list.clear();
        list.addAll(listComponents);
        return printListValues();
    }

    //retorna los valors de la lista
    public List<String> getListValues() {
        return list;
    }

    private String printListValues() {
        return "(" + list.stream()
                .collect(Collectors.joining(" ")) + ")";
    }
}
