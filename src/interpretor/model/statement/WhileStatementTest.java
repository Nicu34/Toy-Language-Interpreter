package interpretor.model.statement;

import interpretor.collection.dictionary.IDictionary;
import interpretor.controller.ProgramStateController;
import interpretor.model.expression.ArithmeticExpression;
import interpretor.model.expression.BooleanExpression;
import interpretor.model.expression.NumberExpression;
import interpretor.model.expression.VariableExpression;
import interpretor.repository.ProgramStateRepository;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by MuresanN on 12/6/2016.
 */
public class WhileStatementTest {

    /**
     * This is the statement for the while loop.
     * <p>
     * start = start + 1;
     * startValue = start;
     */
    private CompoundStatement compoundWhileStatement = new CompoundStatement(
            new AssignStatement("start", new ArithmeticExpression(new VariableExpression("start"), new NumberExpression(1), "+")),
            new AssignStatement("startValue", new VariableExpression("start")));

    /**
     * This is the while loop with its condition as an expression;
     * <p>
     * while (start < end)
     */
    private WhileStatement whileStatement = new WhileStatement(new BooleanExpression(new VariableExpression("start"),
            new VariableExpression("end"), "<"), compoundWhileStatement);

    /**
     * So this is how the while loop looks like. We will initialize the variables start and end in bellow methods.
     *
     *      while (start < end) {
     *          start = start + 1;
     *          startValue = start;
     *      }
     */

    /**
     * Simple check the while loop. The value of start at the end of execution (stored in startValue).
     * Initial variable initialization:
     * <p>
     * start = 0;
     * end = 10;
     * <p>
     * After the execution of the while loop statement the value of start should be 10 (startValue as well).
     */
    @Test
    public void test() throws Exception {
        AssignStatement assignStatementStartVariable = new AssignStatement("start", new NumberExpression(0));
        AssignStatement assignStatementEndVariable = new AssignStatement("end", new NumberExpression(10));

        CompoundStatement compoundAssignStatement = new CompoundStatement(assignStatementStartVariable, assignStatementEndVariable);
        CompoundStatement compoundWhileStatement = new CompoundStatement(compoundAssignStatement, whileStatement);

        ProgramStateRepository programStateRepository = new ProgramStateRepository();
        ProgramStateController programStateController = new ProgramStateController(programStateRepository);

        programStateController.addProgramStatement(compoundWhileStatement);
        programStateController.allStep();

        IDictionary<String, Integer> symbolTable = programStateRepository.getProgramStateList().get(0).getSymbolTable();

        assertEquals(true, symbolTable.containsKey("start"));
        assertEquals(true, symbolTable.containsKey("end"));
        assertEquals(true, symbolTable.containsKey("startValue"));

        assertEquals(((Integer) 10), symbolTable.get("start"));
        assertEquals(((Integer) 10), symbolTable.get("startValue"));
        assertEquals(((Integer) 10), symbolTable.get("end"));
    }

}