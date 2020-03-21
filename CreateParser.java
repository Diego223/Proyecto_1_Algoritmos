/*
* Universidad del Valle de Guatemala
* Algoritmos y estructuras de datos
* Proyecto 1
* @author Diego Crespo 19541
* @author Alejandra GUdiel 19232
* @author Juan Pablo Pineda 19087
* Clase encargada de: parsear las líneas del código
*/

package main;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class CreateParser {
	private static CreateParser createParser;

    private CreateParser() {
    }

    //se crea una nueva instancia 
    public static CreateParser getInstance() {
        if (createParser == null) {
            createParser = new CreateParser();
        }

        //devuelve la instancia creada
        return createParser;
    }

    //parser para las operaciones
    public String parseMultipleOperation(String lispOperation) throws Exception {
        
        //operación de condiciones
        if (lispOperation.contains("cond")) {
            return CreateCondition.getInstance().operateCondLisp(lispOperation);
        }
        //operacion de definir variables setq
        if (lispOperation.contains("setq")) {
            return CreateSetq.getInstance().operateSetQLisp(lispOperation);
        }
        //operacion de listas
        if (lispOperation.contains("list")) {
            return CreateList.getInstance().operateListLisp(lispOperation);
        }
        //operación de funciones
        if (lispOperation.contains("defun")) {
            return CreateDefun.getInstance().operateDefunLisp(lispOperation);
        }
        //operación read (atom)
        if (lispOperation.contains("atom")) {
            return CreateRead.getInstance().operateAtomLisp(lispOperation);
        }
        
        
        while (true) {
        	//se crea un hash map
            Map<String, String> map = new HashMap<>();

            int lastLeftParenthesisIndex = lispOperation.lastIndexOf("(");
            
            if (lastLeftParenthesisIndex < 1) {
                return parseSingleOperation(lispOperation);
            }

            String subOperation = lispOperation.substring(lastLeftParenthesisIndex);
            String operation = subOperation.substring(0, subOperation.indexOf(")") + 1);

            
            String singleResult = parseSingleOperation(operation);

            //ingresa valores al hasmap
            map.put("partOne", lispOperation.substring(0, lastLeftParenthesisIndex));
            map.put("singleResult", singleResult);
            map.put("partTwo", subOperation.substring(subOperation.indexOf(")") + 1));

            //retorna los valores del hasomap
            lispOperation = map.get("partOne") + map.get("singleResult") + map.get("partTwo");
        }
    }

    //progra defensiva
    public Double parseParameter(String number) throws Exception {
        try {
            return Double.parseDouble(number);
        } catch (NumberFormatException e) {
            Optional<Double> parameter = CreateSetq.getInstance().getParameter(number);

            if (parameter.isPresent()) {
                return parameter.get();
            }

            throw new Exception(number + " no se puede operar, o no ha sido declarado :( ");
        }
    }

    //parsear una operacion
    private String parseSingleOperation(String lispOperation) throws Exception {
        int leftParenthesisIndex = lispOperation.indexOf("(");
        int rightParenthesisIndex = lispOperation.indexOf(")");

    
        if (leftParenthesisIndex < 0 || rightParenthesisIndex < 0) {
            throw new Exception("Error, LISP no puede ejecutar eso :( ");
        }

        //Meter valores dentro de un array
        String subOperation = lispOperation.substring(leftParenthesisIndex + 1, rightParenthesisIndex).trim();
        String[] operationComponents = subOperation.split(" ");

        //Regresar operacion de Lisp
        return CreateLisp.getInstance().operateLisp(operationComponents);
    }
}
