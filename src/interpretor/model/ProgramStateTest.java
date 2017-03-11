package interpretor.model;

import interpretor.model.expression.NumberExpression;
import interpretor.model.statement.PrintStatement;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by nicu on 11.11.2016.
 */
public class ProgramStateTest {
    @Test
    public void test() {
        ProgramState programState = new ProgramState(new PrintStatement(new NumberExpression(10)));

        assertEquals(true, programState.isNotCompleted());
        assertNotNull(programState.getExecutionStack());
        assertNotNull(programState.getSymbolTable());
        assertNotNull(programState.getOriginalProgram());
        assertNotNull(programState.getOutputList());
    }
}