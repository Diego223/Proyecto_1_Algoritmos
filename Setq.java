/*
* @author Alejandra Gudiel 19232
* @author Diego Crespo 19541
* @author Juan Pablo Pineda 19087
* Clase: 
*/

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

//Clase para asignar valor a una variable cualquiera
public class SetQ {
	//Clases estaticas
	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,###.##");
    private static Map<String, Double> parameters;
    private static SetQ setQ;

    //Constructor
    private SetQ() {
    }
 
    //GetInstance de la clase
    public static SetQ getInstance() {
        if (setQ == null) {
        	//Nuevo SetQ
            parameters = new HashMap<>();
            setQ = new SetQ();
        }
        //Instancia de SetQ
        return setQ;
    }

    //Metodo para operar funcion setq
    public String operateSetQLisp(String lispOperation) throws Exception {
    	//Busca entre dos parentesis
        int leftParenthesisIndex = lispOperation.indexOf("(");
        int rightParenthesisIndex = lispOperation.indexOf(")");

        //Si no encuentra 2 parentesis
        if (leftParenthesisIndex < 0 || rightParenthesisIndex < 0) {
            throw new Exception("Invalid LISP SetQ operation");
        }

        //Separar componentes de codigo introducido por usuario en un array
        String setQOperation = lispOperation.substring(leftParenthesisIndex + 1, rightParenthesisIndex).trim();
        String[] operationComponents = setQOperation.split(" ");

        //Programacion defensiva en caso introduzca un string en lugar de un array
        Double number;
        try {
        	//Tratar de parsear tercer componente de array
            number = Double.parseDouble(operationComponents[2]);
        } catch (NumberFormatException e) {
        	//Mostrar error
            throw new Exception(operationComponents[2] + " is invalid, only numbers allowed");
        }

        //Ingresar a un mapa los componentes 2 y 3 de array
        parameters.put(operationComponents[1], number);

        //Formatear a decimal el numero ingresado
        return DECIMAL_FORMAT.format(number);
    }

    //Programacion defensiva
    public Optional<Double> getParameter(String parameter) {
    	//Si el mapa contiene valor para variable
        if (parameters.containsKey(parameter)) {
        	//Regresar valor asignado de la variable
            return Optional.of(parameters.get(parameter));
        }

        //Regresar que variable no contiene valor
        return Optional.empty();
    }
}
