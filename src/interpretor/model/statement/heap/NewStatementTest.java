package interpretor.model.statement.heap;

import interpretor.exceptions.model.expression.ExpressionException;
import interpretor.exceptions.model.statement.heap.HeapStatementException;
import interpretor.model.ProgramState;
import interpretor.model.expression.NumberExpression;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by nicu on 12/3/2016.
 */
public class NewStatementTest {

    @Test
    public void test() throws ExpressionException, HeapStatementException{
        NewStatement newStatement = new NewStatement("var", new NumberExpression(10));
        ProgramState programState = new ProgramState(newStatement);

        assertEquals("var", newStatement.getVariableName());
        assertEquals(((Integer) 10), newStatement.getExpression().evaluate(null, null));

        newStatement.execute(programState);

        assertEquals(true, programState.getSymbolTable().containsKey("var"));
    }
}