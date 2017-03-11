package interpretor.model.statement.io;

import interpretor.exceptions.model.statement.StatementExecutionException;
import interpretor.exceptions.model.statement.io.IOInvalidFileFormat;
import interpretor.exceptions.model.statement.io.IOStatementException;
import interpretor.model.ProgramState;
import interpretor.model.expression.NumberExpression;
import interpretor.model.expression.VariableExpression;
import interpretor.model.statement.PrintStatement;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by nicu on 11/20/2016.
 */
public class ReadFileStatementTest {
    @Test
    public void testWithMultipleIntegers() throws IOStatementException, StatementExecutionException {
        // first we have to open the file
        ProgramState programState = new ProgramState(new PrintStatement(new VariableExpression("variable")));
        OpenRFileStatement openRFileStatement = new OpenRFileStatement("multipleIntegers", "test-1");
        ReadFileStatement readFileStatement = new ReadFileStatement(new VariableExpression("multipleIntegers"), "variable");

        openRFileStatement.execute(programState);
        readFileStatement.execute(programState);

        assertEquals(((Integer) 3), programState.getSymbolTable().get("variable"));
    }

    @Test
    public void testWithOneInteger() throws IOStatementException, StatementExecutionException {
        // first we have to open the file
        ProgramState programState = new ProgramState(new PrintStatement(new VariableExpression("variable")));
        OpenRFileStatement openRFileStatement = new OpenRFileStatement("oneInteger", "test-2");
        ReadFileStatement readFileStatement = new ReadFileStatement(new VariableExpression("oneInteger"), "variable");

        openRFileStatement.execute(programState);
        readFileStatement.execute(programState);

        assertEquals(((Integer) 34), programState.getSymbolTable().get("variable"));
    }

    @Test
    public void testEmptyFile() throws IOStatementException, StatementExecutionException {
        // first we have to open the file
        ProgramState programState = new ProgramState(new PrintStatement(new VariableExpression("variable")));
        OpenRFileStatement openRFileStatement = new OpenRFileStatement("emptyFile", "test-empty-file");
        ReadFileStatement readFileStatement = new ReadFileStatement(new VariableExpression("emptyFile"), "variable");

        openRFileStatement.execute(programState);
        readFileStatement.execute(programState);

        assertEquals(1, programState.getSymbolTable().size());
        assertEquals(true, programState.getSymbolTable().containsKey("emptyFile"));
    }

    @Test (expected = IOInvalidFileFormat.class)
    public void testInvalidFile() throws IOStatementException, StatementExecutionException {
        // first we have to open the file
        ProgramState programState = new ProgramState(new PrintStatement(new VariableExpression("variable")));
        OpenRFileStatement openRFileStatement = new OpenRFileStatement("invalidFile", "test-invalid-file");
        ReadFileStatement readFileStatement = new ReadFileStatement(new VariableExpression("invalidFile"), "variable");

        openRFileStatement.execute(programState);
        readFileStatement.execute(programState);

        assertEquals(1, programState.getSymbolTable().size());
        assertEquals(true, programState.getSymbolTable().containsKey("invalidFile"));
    }

    @Test (expected = IOInvalidFileFormat.class)
    public void testInvalidFile2() throws IOStatementException, StatementExecutionException {
        // first we have to open the file
        ProgramState programState = new ProgramState(new PrintStatement(new NumberExpression(10)));
        OpenRFileStatement openRFileStatement = new OpenRFileStatement("invalidFile1", "test-invalid-file1");
        ReadFileStatement readFileStatement = new ReadFileStatement(new VariableExpression("invalidFile1"), "variable");

        openRFileStatement.execute(programState);
        readFileStatement.execute(programState);

        assertEquals(1, programState.getSymbolTable().size());
        assertEquals(true, programState.getSymbolTable().containsKey("invalidFile1"));
    }
}