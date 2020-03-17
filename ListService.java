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


import main.service.ParserService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


//Clase para meter valores dentro de una lista
public class ListService {
	//Clases estaticas
	private static List<String> lista;
    private static ListService ServicioLista;

    //Constructor
    private ListService() {
    }

    //Get Instance de la clase
    public static ListService getInstance() {
        if (ServicioLista == null) {
        	//Nuevas instancias
            lista = new ArrayList<>();
            ServicioLista = new ListService();
        }
        //Instancia de ServicioLista
        return ServicioLista;
    }
    
    //Metodo para operar funcion de lista
    public String operateListLisp(String lispOperation) throws Exception {
    	//Buscar entre dos parentesis
        int ParentesisIzq = lispOperation.indexOf("(");
        int ParentesisDer = lispOperation.indexOf(")");

        //Si no encuentra dos parentesis
        if (ParentesisIzq < 0 || ParentesisDer < 0) {
            throw new Exception("Invalid LISP SetQ operation");
        }

        //Separar componentes dentro de los parentesis en un array
        String OpSetQ = lispOperation.substring(ParentesisIzq + 1, ParentesisDer).trim();
        String[] Componentes = OpSetQ.split(" ");

        //Meter valores obtenidos dentro de una lista
        List<String> ComponentesLista = new ArrayList<>(Arrays.asList(Componentes));
        //Remover variable lista de la lista
        ComponentesLista.remove(0);
        
        //Parsear parametros
        for (String component : ComponentesLista) {
            ParserService.getInstance().parseParameter(component);
        }
        
        //Anadir componentes a otra lista
        lista.clear();
        lista.addAll(ComponentesLista);

        //Regresar valores de la lista
        return printListValues();
    }

    //retornar valores dentro de la lista
    public List<String> getListValues() {
        return lista;
    }

    //metodo para regresar valores de la lista 
    private String printListValues() {
        return "(" + lista.stream()
                .collect(Collectors.joining(" ")) + ")";
    }
}
