package interpretor.model.statement.io;

import interpretor.exceptions.model.statement.StatementException;
import interpretor.exceptions.model.statement.StatementExecutionException;
import interpretor.exceptions.model.statement.io.IOFileNotFoundException;
import interpretor.exceptions.model.statement.io.IOStatementException;
import interpretor.model.ProgramState;
import interpretor.model.expression.NumberExpression;
import interpretor.model.expression.VariableExpression;
import interpretor.model.statement.AssignStatement;
import interpretor.model.statement.IStatement;
import interpretor.model.statement.PrintStatement;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by nicu on 11/20/2016.
 */
public class CloseRFileStatementTest {
    @Test
    public void testCloseFileSuccessfully() throws IOStatementException, StatementExecutionException {
        // first let's open it
        OpenRFileStatement openRFileStatement = new OpenRFileStatement("varFieldId", "test");
        PrintStatement printStatement = new PrintStatement(new VariableExpression("varFieldId"));
        ProgramState programState = new ProgramState(printStatement);

        openRFileStatement.execute(programState);

        CloseRFileStatement closeRFileStatement = new CloseRFileStatement(new VariableExpression("varFieldId"));

        closeRFileStatement.execute(programState);

        assertEquals(true, programState.getFileTable().isEmpty());
        assertEquals(true, programState.getSymbolTable().containsKey("varFieldId"));
        assertEquals(true, programState.getSymbolTable().containsValue(1));
    }

    @Test (expected = StatementExecutionException.class)
    public void testStatementExecutionException() throws IOStatementException, StatementExecutionException{
        new CloseRFileStatement(new VariableExpression("NU EXISTA")).execute(new ProgramState(new PrintStatement(new VariableExpression("da"))));
    }

    @Test (expected = IOFileNotFoundException.class)
    public void testFileNotFoundException() throws IOStatementException, StatementExecutionException {
        AssignStatement assignStatement = new AssignStatement("variable", new NumberExpression(10));
        ProgramState programState = new ProgramState(assignStatement);

        assignStatement.execute(programState);

        new CloseRFileStatement(new VariableExpression("variable")).execute(programState);
    }
}