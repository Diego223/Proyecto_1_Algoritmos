/*
* Universidad del Valle de Guatemala
* Algoritmos y estructuras de datos
* Proyecto 1
* @author Diego Crespo 19541
* @author Alejandra GUdiel 19232
* @author Juan Pablo Pineda 19087
* Clase encargada de: 
*/

package main;

import java.util.List;

//Clase encargada de revisar si parametro o valor se encuentra dentro de una lista
public class AtomService {
  //Clase static
  private static AtomService atomService;

  //Constructor
  private AtomService() {
  }

  //Get Instance de la clase
  public static AtomService getInstance() {
    if (atomService == null) {
      //Nuevo Atom Service
      atomService = new AtomService();
    }
    //Instancia de AtomService
    return atomService;
  }

  //Metodo para operar la funcion atom
  public String operateAtomLisp(String lispOperation) throws Exception {
    //Buscar entre parentesis
    int ParentesisIzq = lispOperation.indexOf("(");
    int ParentesisDer = lispOperation.indexOf(")");

    //Si no encuentra parentesis
    if (ParentesisIzq < 0 || ParentesisDer < 0) {
      //Programacion defensiva
      throw new Exception("Invalid LISP operation");
    }

    //Separar codigo introducido por el usuario por espacios " "
    String SubOp = lispOperation.substring(ParentesisIzq + 1, ParentesisDer).trim();
    //Guardar componentes dentro de un array
    String[] Componentes = SubOp.split(" ");

    //Revisa en clase List si el valor que el usuario ingreso se encuentra dentro de la lista
    List<String> listVals = ListService.getInstance().getListValues();

    //si la lista si contiene el valor o parametro que ingreso el usuario
    if (listVals.contains(Componentes[1])) {
      return "true";
    }
    //si la lista no contiene el valor o parametro que ingreso el usuario
    return "false";
  }
}
