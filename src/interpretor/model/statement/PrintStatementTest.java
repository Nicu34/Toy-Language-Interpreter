package interpretor.model.statement;

import interpretor.exceptions.model.expression.ExpressionException;
import interpretor.exceptions.model.statement.StatementException;
import interpretor.model.ProgramState;
import interpretor.model.expression.NumberExpression;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by nicu on 11.11.2016.
 */
public class PrintStatementTest {

    @Test
    public void test() throws StatementException{
        PrintStatement printStatement = new PrintStatement(new NumberExpression(10));
        ProgramState    programState = new ProgramState(printStatement);

        printStatement.execute(programState);

        assertEquals(((int) programState.getOutputList().get(0)), 10);
        assertEquals(programState.getSymbolTable().size(), 0);
    }
}