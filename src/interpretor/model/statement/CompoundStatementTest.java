package interpretor.model.statement;

import interpretor.model.ProgramState;
import interpretor.model.expression.NumberExpression;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by nicu on 10.11.2016.
 */
public class CompoundStatementTest {
    @Test
    public void test() {
        AssignStatement assignStatement1 = new AssignStatement("var1", new NumberExpression(1));
        AssignStatement assignStatement2 = new AssignStatement("var2", new NumberExpression(2));

        CompoundStatement compoundStatement = new CompoundStatement(assignStatement1, assignStatement2);
        ProgramState programState = new ProgramState(compoundStatement);

        compoundStatement.execute(programState);
        assertEquals(programState.getExecutionStack().pop(), assignStatement1);
        assertEquals(programState.getExecutionStack().pop(), assignStatement2);
    }
}