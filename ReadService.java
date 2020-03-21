/*
* Universidad del Valle de Guatemala
* Algoritmos y estructuras de datos
* Proyecto 1
* @author Diego Crespo 19541
* @author Alejandra GUdiel 19232
* @author Juan Pablo Pineda 19087
* Clase encargada de: realizar lectura y escritura de variables
*/

package main;

import java.util.List;


public class ReadService {
  
  private static ReadService readService;

  
  private ReadService() {
  }

  //se crea la instancia
  public static ReadService getInstance() {
    if (readService == null) {
      //Nuevo Atom Service
      readService = new ReadService();
    }
    //devuelve un ReadService
    return readService;
  }

  //método para realizar la operación
  public String operateReadService(String lispOperation) throws Exception {
    
    int leftParenthesisIndex = lispOperation.indexOf("(");
    int rightParenthesisIndex = lispOperation.indexOf(")");

    if (leftParenthesisIndex < 0 || rightParenthesisIndex < 0) {
      
        //progra defensiva
      throw new Exception("Invalid LISP operation");
    }

    
    String subOperation = lispOperation.substring(leftParenthesisIndex + 1, rightParenthesisIndex).trim();
    
    //guarda lo ingresado en un array para luego revisar si está en la lista
    String[] operationComponents = subOperation.split(" ");

    //revisa si hay valores en la lista
    List<String> listValues = ListService.getInstance().getListValues();

    //if para encontrar lo solicitado por el usuario
    if (listValues.contains(operationComponents[1])) {
      return "true";
    }
    
    return "false";
  }
}
