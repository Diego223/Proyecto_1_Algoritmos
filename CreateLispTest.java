import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 
 */

/**
 * @author alegudiel
 *
 */
class CreateLispTest {

	String comp1 = "1";
    String comp2 = "2";
    String lispOperation = "";
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,###.##");


    // hay varios tests del mismo metodo ya que se hicieron todas las operaciones utilizando el mismo metodo

    //SUMA
    @Test
    public void suma() throws Exception {
        lispOperation = "(+ 1 2)";
        String result1 = CreateParser.getInstance().parseMultipleOperation(lispOperation);
        assertEquals("3",result1);
    }

    //RESTA
    @Test
    public void resta() throws Exception {
        lispOperation = "(- 2 2)";
        String result1 = CreateParser.getInstance().parseMultipleOperation(lispOperation);
        assertEquals("0",result1);
    }
    //MULTIPLICACION
    @Test
    public void multiplicacion() throws Exception {
        lispOperation = "(* 1 2)";
        String result1 = CreateParser.getInstance().parseMultipleOperation(lispOperation);
        assertEquals("2",result1);
    }
    //DIVISION
    @Test
    public void division() throws Exception {
        lispOperation = "(/ 2 2)";
        String operator = "+";
        String result1 = CreateParser.getInstance().parseMultipleOperation(lispOperation);
        assertEquals("1",result1);
    }
    // >
    @Test
    public void greaterthan() throws Exception {
        lispOperation = "(> 1 2)";
        String result1 = CreateParser.getInstance().parseMultipleOperation(lispOperation);
        assertEquals("false",result1);
    }
    // <
    @Test
    public void lesserthan() throws Exception {
        lispOperation = "(< 1 2)";
        String result1 = CreateParser.getInstance().parseMultipleOperation(lispOperation);
        assertEquals("true",result1);
    }
    // ==
    @Test
    public void equal() throws Exception {
        lispOperation = "(== 1 2)";
        String result1 = CreateParser.getInstance().parseMultipleOperation(lispOperation);
        assertEquals("false",result1);
    }
    // !=
    @Test
    public void different() throws Exception {
        lispOperation = "(!= 1 2)";
        String result1 = CreateParser.getInstance().parseMultipleOperation(lispOperation);
        assertEquals("true",result1);
    }

}
