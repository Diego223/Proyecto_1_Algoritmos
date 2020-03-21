/*
* Universidad del Valle de Guatemala
* Algoritmos y estructuras de datos
* Proyecto 1
* @author Diego Crespo 19541
* @author Alejandra GUdiel 19232
* @author Juan Pablo Pineda 19087
* Clase encargada de: crear funciones para el sistema lisp
*/

package main;

import main.CreateParser;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CreateDefun {
	private static Map<String, Function> functions;
    private static CreateDefun CreateDefun;

    private CreateDefun() {
    }

    //se crea una instancia nueva
    public static CreateDefun getInstance() {
        if (CreateDefun == null) {
            functions = new HashMap<>();
            CreateDefun = new CreateDefun();
        }
        //devuelve la instancia creada
        return CreateDefun;
    }
    
    //opeera el defun
    public String operateDefunLisp(String lispOperation) throws Exception {
    	
        lispOperation = lispOperation.substring(lispOperation.indexOf(" ") + 1);
        
        //nombre de la funcion
        String functionName = lispOperation.substring(0, lispOperation.indexOf(" "));
        lispOperation = lispOperation.substring(lispOperation.indexOf(" ") + 1);
 
        //parametros de la funcion
        String functionParameters = lispOperation.substring(0, lispOperation.indexOf(")") + 1);
        lispOperation = lispOperation.substring(lispOperation.indexOf(")") + 1);

        //operaciones de la funcion
        lispOperation = lispOperation.substring(lispOperation.indexOf(" ") + 1);
        String functionDeclaration = lispOperation.substring(0, lispOperation.lastIndexOf(")"));;

        //se crea la funcion
        Function function = new Function();
        function.setParameterQuantity(getParameterQuantity(functionParameters));
        function.setParameters(getParameters(functionParameters));
        function.setFunction(functionDeclaration);

        validateFunctionDeclaration(function);
        
        functions.put(functionName, function);

        //se muestra el nombre de la función creada
        return "La función creada es: " + functionName.toLowerCase() ;
    }

    //progra defensiva
    public Optional<Function> getDefun(String functionName) {

        if (functions.containsKey(functionName)) {
            return Optional.of(functions.get(functionName));
        }
        
        return Optional.empty();
    }

    //operar la funcion
    public String operateDefunFunctionLisp(Function function, String[] operationComponents) throws Exception {
        int operationParametersLength = operationComponents.length - 1;
        Integer parameterQuantity = function.getParameterQuantity();

        //se muestra error si la funcion no se encuentra
        if (operationParametersLength != parameterQuantity) {
            throw new Exception("Error con la cantidad de parametros ingresados: " + parameterQuantity + " se esperan: " + operationParametersLength);
        }

        //se parsea la funcion
        String functionStatement = function.getFunction();
        //
        for (int i = 0; i < operationParametersLength; i++) {
            String parameter = function.getParameters().get(i);
            functionStatement = functionStatement.replace(parameter, operationComponents[i + 1]);
        }
        return CreateParser.getInstance().parseMultipleOperation(functionStatement);
    }


    //validacion de funciones
    private void validateFunctionDeclaration(Function function) throws Exception {
        Integer parameterQuantity = function.getParameterQuantity();

        //caracteristicas de la funcion creada
        String functionStatement = function.getFunction();
        for (int i = 0; i < parameterQuantity; i++) {
            String parameter = function.getParameters().get(i);
            functionStatement = functionStatement.replace(parameter, "1");
        }
        
        CreateParser.getInstance().parseMultipleOperation(functionStatement);
    }

    private Integer getParameterQuantity(String parameters) {
        return parameters.split(",").length;
    }

    //se crea un hah map 
    private Map<Integer, String> getParameters(String parameters) {
        Map<Integer, String> parametersMap = new HashMap<>();

        String[] parametersSplit = parameters.split(",");

        for (int i = 0; i < parametersSplit.length; i++) {
            String parameter = parametersSplit[i]
                    .replace(")", "")
                    .replace("(", "")
                    .trim();

            parametersMap.put(i, parameter);
        }

        //se regresa el mapa creado
        return parametersMap;
    }

    //clase para funciones
    public static class Function {
    	//Parametros
        private Integer parameterQuantity;
        private Map<Integer, String> parameters;
        private String function;

        Integer getParameterQuantity() {
            return parameterQuantity;
        }

        //parametris
        void setParameterQuantity(Integer parameterQuantity) {
            this.parameterQuantity = parameterQuantity;
        }

        //nuevo mapa de int = string
        Map<Integer, String> getParameters() {
            return parameters;
        }

        void setParameters(Map<Integer, String> parameters) {
            this.parameters = parameters;
        }

        //se regresa la funcoin
        String getFunction() {
            return function;
        }

        //se declara la funcoin
        void setFunction(String function) {
            this.function = function;
        }
    }
}
