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


//Clase encargada de una condicion (if) para dos parametros
public class CondService {
	//Clase Static
	private static CondService CondSer;

	//Constructor
    private CondService() {
    }

    //Get Instance de la clase
    public static CondService getInstance() {
        if (CondSer == null) {
        	//Nuevo CondService
            CondSer = new CondService();
        }

        //Instancia de CondService
        return CondSer;
    }

    //Metodo para hacer funcion cond
    public String operateCondLisp(String lispOperation) throws Exception {
    	//Separar componentes dentro del parentesis en un array
        String[] COC = lispOperation.split("\\(");

        //Declarar tercer valor de parentesis como condicional
        String condString = COC[2];
        //Declarar condicional
        String conditional = condString.substring(0, condString.indexOf(")"));

        //Separar por espacios
        String[] Componentes = conditional.split(" ");

        //operacion para que identifique operaciones
        Boolean Result = Boolean.valueOf(LispService.getInstance().operateLisp(Componentes));
        
        if(Result) {
        	String CondTrue = COC[3];
        	return CondTrue.substring(0, CondTrue.indexOf(")"));
        }
        
        String CondFalse = COC[4];
        return CondFalse.substring(0, CondFalse.indexOf(")"));
    }

}
