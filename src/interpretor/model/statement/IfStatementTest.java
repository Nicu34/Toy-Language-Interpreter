package interpretor.model.statement;

import interpretor.exceptions.model.expression.ExpressionException;
import interpretor.exceptions.model.statement.StatementException;
import interpretor.model.ProgramState;
import interpretor.model.expression.NumberExpression;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by nicu on 10.11.2016.
 */
public class IfStatementTest {

    @Test
    public void test() throws StatementException{
        AssignStatement assignStatement1 = new AssignStatement("var1", new NumberExpression(10));
        AssignStatement assignStatement2 = new AssignStatement("var2", new NumberExpression(20));
        NumberExpression numberExpression = new NumberExpression(1);
        IfStatement ifStatement = new IfStatement(numberExpression, assignStatement1, assignStatement2);
        ProgramState programState = new ProgramState(ifStatement);

        ifStatement.execute(programState);

        assertEquals(programState.getSymbolTable().containsValue(10), true);
        assertEquals(programState.getSymbolTable().containsKey("var1"), true);
    }
}