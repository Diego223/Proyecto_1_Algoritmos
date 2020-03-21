/*
* Universidad del Valle de Guatemala
* Algoritmos y estructuras de datos
* Proyecto 1
* @author Diego Crespo 19541
* @author Alejandra GUdiel 19232
* @author Juan Pablo Pineda 19087
* Clase encargada de: crear condiciones 
*/

package main;

import main.CreateLisp;


public class CreateCondition {
	//Clase Static
	private static CreateCondition createCondition;

	//Constructor
    private CreateCondition() {
    }

    //Get Instance de la clase
    public static CreateCondition getInstance() {
        if (createCondition == null) {
        	//Nuevo CreateCondition
            createCondition = new CreateCondition();
        }

        //Instancia de CreateCondition
        return createCondition;
    }

    //método para crear la función de cond
    public String operateCondLisp(String lispOperation) throws Exception {
        
        
        String[] condOperationComponents = lispOperation.split("\\(");
        String conditionalString = condOperationComponents[2];
        String conditional = conditionalString.substring(0, conditionalString.indexOf(")"));
        String[] operationComponents = conditional.split(" ");

        //identifica operaciones
        Boolean result = Boolean.valueOf(CreateLisp.getInstance().operateLisp(operationComponents));
        
        if(result) {
        	String conditionTrue = condOperationComponents[3];
        	return conditionTrue.substring(0, conditionTrue.indexOf(")"));
        }
        
        String conditionFalse = condOperationComponents[4];
        return conditionFalse.substring(0, conditionFalse.indexOf(")"));
    }

}
