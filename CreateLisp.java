/*
* Universidad del Valle de Guatemala
* Algoritmos y estructuras de datos
* Proyecto 1
* @author Diego Crespo 19541
* @author Alejandra GUdiel 19232
* @author Juan Pablo Pineda 19087
* Clase encargada de: crear las operaciones de lisp
*/


package main;

import main.CreateDefun;
import java.text.DecimalFormat;
import java.util.Optional;


public class CreateLisp {
	
	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,###.##");
    private static CreateLisp createLisp;

    
    private CreateLisp() {
    }

    //se crea una nueva instancia
    public static CreateLisp getInstance() {
        if (createLisp == null) {
        
            createLisp = new CreateLisp();
        }
        //se devuelve la instancia
        return createLisp;
    }

    //Metodo para hacer operacion Lisp
    public String operateLisp(String[] operationComponents) throws Exception {
        String operator = operationComponents[0];

        //detecta que operación se realiza
        switch (operator) {
        	//Operaciones aritmeticas
            case "+":
            case "-":
            case "*":
            case "/":
            	
                Double result = processArithmeticOperation(operator, operationComponents[1], operationComponents[2]);
                return DECIMAL_FORMAT.format(result);
            
                //operación de igualdad
            case "eq":
            	
                return "" + processEqualityOperation(operationComponents[1], operationComponents[2]);

                //operaciones de comparación
            case ">":
            case "<":
            case "==":
            case "!=":
            	
                return "" + processConditionalOperation(operator, operationComponents[1], operationComponents[2]);

            
            default:
                final Optional<CreateDefun.Function> optionalDefun = CreateDefun.getInstance().getDefun(operator);
                if (optionalDefun.isPresent()) {
                    return CreateDefun.getInstance().operateDefunFunctionLisp(optionalDefun.get(), operationComponents);
                }
                
            
                throw new Exception(operator + " no puedes hacer eso :( ");
        }
    }

    //operaciones especiales
    private boolean processConditionalOperation(String operator, String numberOne, String numberTwo) throws Exception {
    	//array nuevo
        Double[] numbers = new Double[2];
        //se parsea el array
        numbers[0] = CreateParser.getInstance().parseParameter(numberOne);
        numbers[1] = CreateParser.getInstance().parseParameter(numberTwo);

        
        switch (operator) {
            case ">":
                return numbers[0] > numbers[1];
            case "<":
                return numbers[0] < numbers[1];
            case "==":
                return numbers[0].equals(numbers[1]);
            case "!=":
                return !numbers[0].equals(numbers[1]);
            default:
                throw new Exception(operator + " - operator not supported");
        }
    }

    //método para la operación de igualdad
    private boolean processEqualityOperation(String parameterOne, String parameterTwo) {
        return parameterOne.equals(parameterTwo);
    }

    //método para las aritméticas
    private Double processArithmeticOperation(String operator, String numberOne, String numberTwo) throws Exception {
        Double[] numbers = new Double[2];
        numbers[0] = CreateParser.getInstance().parseParameter(numberOne);
        numbers[1] = CreateParser.getInstance().parseParameter(numberTwo);

        switch (operator) {
            case "+":
                return numbers[0] + numbers[1];
            case "-":
                return numbers[0] - numbers[1];
            case "*":
                return numbers[0] * numbers[1];
            case "/":
                return numbers[0] / numbers[1];
            default:
                throw new Exception(operator + " - operator not supported");
        }
}
}
