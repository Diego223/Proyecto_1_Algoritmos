
/*
* @author Alejandra Gudiel 19232
* @author Diego Crespo 19541
* @author Juan Pablo Pineda 19087
* Clase: 
*/

import Atom.java;
import Cond.java;
import Defun.java;
import Listss.java;
import Setq.java;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


//Clase encargada de hacer parse en cada linea de codigo
public class Parser {
	//Clase Static
	private static Parser parser;

	//Constructor
    private Parser() {
    }

    //Get Instance de la clase
    public static Parser getInstance() {
        if (parser == null) {
        	//nueva Instancia
            parser = new Parser();
        }

        //Instancia tipo parser
        return parser;
    }

    //Para parsear multiples operaciones
    public String parseMultipleOperation(String lispOperation) throws Exception {
    	//Si la operacion es cond
        if (lispOperation.contains("cond")) {
        	//operar Instancia Cond-
            return Cond.getInstance().operateCondLisp(lispOperation);
        }
        //Si la operacion es setq
        if (lispOperation.contains("setq")) {
        	//operar Instancia SetQ-
            return SetQ.getInstance().operateSetQLisp(lispOperation);
        }
        //Si la operacion es list
        if (lispOperation.contains("list")) {
        	//operar Instancia List-
            return Listss.getInstance().operateListLisp(lispOperation);
        }
        //Si la operacion es defun
        if (lispOperation.contains("defun")) {
        	//operar Instancia Defun-
            return Defund.getInstance().operateDefunLisp(lispOperation);
        }
        //Si la operacion es atom
        if (lispOperation.contains("atom")) {
        	//operar Instancia Atom-
            return Atom.getInstance().operateAtomLisp(lispOperation);
        }
        
        //Si es alguno de los anteriores
        while (true) {
        	//Nuevo HashMap string = string
            Map<String, String> map = new HashMap<>();

            //Buscar cuantos parentesis "(" hay
            int lastLeftParenthesisIndex = lispOperation.lastIndexOf("(");

            //Si solo tiene un parentesis "("
            if (lastLeftParenthesisIndex < 1) {
            	//Operar SingleOperation
                return parseSingleOperation(lispOperation);
            }

            //Buscar strings entre parentesis
            String subOperation = lispOperation.substring(lastLeftParenthesisIndex);
            String operation = subOperation.substring(0, subOperation.indexOf(")") + 1);

            //Parsear single Operation
            String singleResult = parseSingleOperation(operation);

            //Meter variables con valores en HashMap
            map.put("partOne", lispOperation.substring(0, lastLeftParenthesisIndex));
            map.put("singleResult", singleResult);
            map.put("partTwo", subOperation.substring(subOperation.indexOf(")") + 1));

            //Regresar valores dentro de HashMap
            lispOperation = map.get("partOne") + map.get("singleResult") + map.get("partTwo");
        }
    }

    

    //Parsear single operation
    private String parseSingleOperation(String lispOperation) throws Exception {
    	//buscar Parentesis
        int leftParenthesis = lispOperation.indexOf("(");
        int rightParenthesis = lispOperation.indexOf(")");

        //Si no encuentra 2 parentesis
        if (leftParenthesis < 0 || rightParenthesis < 0) {
        	//Mensaje de error
            throw new Exception("Invalid LISP operation");
        }

        //Meter valores dentro de un array
        String subOperation = lispOperation.substring(leftParenthesis + 1, rightParenthesis).trim();
        String[] operationComponents = subOperation.split(" ");

        //Regresar operacion de Lisp
        return Lisp.getInstance().operateLisp(operationComponents);
    }
}
