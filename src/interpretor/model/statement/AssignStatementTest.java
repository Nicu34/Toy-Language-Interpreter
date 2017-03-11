package interpretor.model.statement;

import interpretor.exceptions.model.expression.ExpressionException;
import interpretor.exceptions.model.statement.StatementException;
import interpretor.exceptions.runtime.ClassCastException;
import interpretor.model.ProgramState;
import interpretor.model.expression.NumberExpression;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by nicu on 10.11.2016.
 */
public class AssignStatementTest {
    @Test
    public void    test() throws StatementException{
        AssignStatement assignStatement = new AssignStatement("ten", new NumberExpression(10));

        ProgramState programState = new ProgramState(assignStatement);
        assignStatement.execute(programState);

        assertEquals(programState.getSymbolTable().containsKey("ten"), true);
        assertEquals(programState.getSymbolTable().containsValue(10), true);
    }

    @Test(expected = ClassCastException.class)
    public void    testClassCast() {
        AssignStatement assignStatement = new AssignStatement(10, new NumberExpression(10));
    }
}