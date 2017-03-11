package interpretor.repository;

import interpretor.exceptions.repository.RepositoryException;
import interpretor.model.ProgramState;
import interpretor.model.expression.NumberExpression;
import interpretor.model.statement.CompoundStatement;
import interpretor.model.statement.IfStatement;
import interpretor.model.statement.PrintStatement;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by nicu on 11.11.2016.
 */
public class ProgramStateRepositoryTest {

    @Test
    public void test() throws RepositoryException{
        ProgramStateRepository programStateRepository = new ProgramStateRepository("file.txt");

        PrintStatement printStatement = new PrintStatement(new NumberExpression(10));
        IfStatement ifStatement = (new IfStatement(new NumberExpression(1), printStatement, printStatement));
        CompoundStatement compoundStatement = new CompoundStatement(printStatement, ifStatement);

        programStateRepository.setMainProgramStatement(compoundStatement);

        Assert.assertEquals(new CompoundStatement(printStatement, ifStatement), programStateRepository.getProgramStateList().get(0).getOriginalProgram());

    }

    @Test
    public void test_serializableProgramState() throws RepositoryException {
        ProgramStateRepository programStateRepository = new ProgramStateRepository("serializable.txt");
        PrintStatement printStatement = new PrintStatement(new NumberExpression(10));
        ProgramState initialProgramState = new ProgramState(printStatement);

        programStateRepository.setMainProgramStatement(printStatement);
        programStateRepository.serializeMainProgramState();
        programStateRepository.deserializeMainProgramState();

        Assert.assertEquals(initialProgramState, programStateRepository.getProgramStateList().get(0));
    }
}