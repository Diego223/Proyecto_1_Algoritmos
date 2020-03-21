/*
* Universidad del Valle de Guatemala
* Algoritmos y estructuras de datos
* Proyecto 1
* @author Diego Crespo 19541
* @author Alejandra GUdiel 19232
* @author Juan Pablo Pineda 19087
* Clase encargada de: asiganr valores a las variables
*/

package main;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class CreateSetq {
	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,###.##");
    private static Map<String, Double> parameters;
    private static CreateSetq createSetq;

    private CreateSetq() {
    }
 
    //se crea una nuevva instancia
    public static CreateSetq getInstance() {
        if (createSetq == null) {
        	//Nuevo CreateSetq
            parameters = new HashMap<>();
            createSetq = new CreateSetq();
        }
        return createSetq;
    }

    //método para operar el setq
    public String operateSetQLisp(String lispOperation) throws Exception {
    	
        int leftParenthesisIndex = lispOperation.indexOf("(");
        int rightParenthesisIndex = lispOperation.indexOf(")");

        if (leftParenthesisIndex < 0 || rightParenthesisIndex < 0) {
            throw new Exception("No puedes hacer esa operación :( ");
        }

        //se crea un array 
        String setQOperation = lispOperation.substring(leftParenthesisIndex + 1, rightParenthesisIndex).trim();
        String[] operationComponents = setQOperation.split(" ");

        //progra defensiva
        Double number;
        try {
            number = Double.parseDouble(operationComponents[2]);
        } catch (NumberFormatException e) {
            throw new Exception(operationComponents[2] + " operación inválida :( ");
        }

        //se ingresa a un mapa los componentes
        parameters.put(operationComponents[1], number);
        return DECIMAL_FORMAT.format(number);
    }

    //progra defensiva
    public Optional<Double> getParameter(String parameter) {
        
        if (parameters.containsKey(parameter)) {
            return Optional.of(parameters.get(parameter));
        }

        return Optional.empty();
    }
}
