package interpretor.model.statement.io;

import interpretor.exceptions.model.statement.io.IOFileAlreadyExistsException;
import interpretor.exceptions.model.statement.io.IOFileNotFoundException;
import interpretor.exceptions.model.statement.io.IOStatementException;
import interpretor.model.ProgramState;
import interpretor.model.expression.VariableExpression;
import interpretor.model.statement.PrintStatement;
import interpretor.utils.FilePair;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.Assert.assertEquals;

/**
 * Created by nicu on 11/20/2016.
 */
public class OpenRFileStatementTest {

    @Test
    public void testOpenRFileStatement() throws IOStatementException {
        OpenRFileStatement openRFileStatement = new OpenRFileStatement("varFieldId", "test");
        PrintStatement printStatement = new PrintStatement(new VariableExpression("varFieldId"));
        ProgramState programState = new ProgramState(printStatement);

        openRFileStatement.execute(programState);

        assertEquals(true, programState.getFileTable().containsKey(1));
        assertEquals(true, programState.getFileTable().containsValue(new FilePair("test")));
        assertEquals(true, programState.getSymbolTable().containsKey("varFieldId"));
        assertEquals(true, programState.getSymbolTable().containsValue(1));
    }

    @Test(expected = IOFileAlreadyExistsException.class)
    public void testIOFileAlreadyExistsException() throws IOStatementException {
        OpenRFileStatement openRFileStatement = new OpenRFileStatement("varFieldId", "test");
        PrintStatement printStatement = new PrintStatement(new VariableExpression("varFieldId"));
        ProgramState programState = new ProgramState(printStatement);

        openRFileStatement.execute(programState);
        openRFileStatement.execute(programState);
    }

    @Test(expected = IOFileNotFoundException.class)
    public void testIOFileNotFoundException() throws IOStatementException {
        OpenRFileStatement openRFileStatement = new OpenRFileStatement("varFieldId", "NU EXISTA");
        PrintStatement printStatement = new PrintStatement(new VariableExpression("varFieldId"));
        ProgramState programState = new ProgramState(printStatement);

        openRFileStatement.execute(programState);
    }
}