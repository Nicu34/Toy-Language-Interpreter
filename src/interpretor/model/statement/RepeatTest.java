package interpretor.model.statement;

import interpretor.controller.ProgramStateController;
import interpretor.exceptions.controller.ControllerExecutionException;
import interpretor.model.expression.ArithmeticExpression;
import interpretor.model.expression.BooleanExpression;
import interpretor.model.expression.NumberExpression;
import interpretor.model.expression.VariableExpression;
import interpretor.repository.ProgramStateRepository;
import org.junit.Test;

/**
 * Created by nicu on 1/26/2017.
 */
public class RepeatTest {
    /*
    v=0;
(repeat (fork(print(v);v=v-1);v=v+1) until v==3);
x=1;y=2;z=3;w=4;
print(v*10)
     */
    CompoundStatement compoundStatement = new CompoundStatement(
            new PrintStatement(new VariableExpression("v")),
            new AssignStatement("v",
                    new ArithmeticExpression(new VariableExpression("v"), new NumberExpression(1), "-")));

    ForkStatement forkStatement = new ForkStatement(compoundStatement);

    CompoundStatement repeatBodyStatement = new CompoundStatement(forkStatement, new AssignStatement("v",
            new ArithmeticExpression(new VariableExpression("v"), new NumberExpression(1), "+")));

    RepeatStatement repeatStatement = new RepeatStatement(repeatBodyStatement, new BooleanExpression(new VariableExpression("v"), new NumberExpression(-3), "=="));

    CompoundStatement compoundStatement1 = new CompoundStatement(new AssignStatement("v", new NumberExpression(0)), repeatStatement);

    CompoundStatement compoundStatement2 = new CompoundStatement(
            new CompoundStatement(new AssignStatement("x", new NumberExpression(1)), new AssignStatement("y", new NumberExpression(2))),
            new CompoundStatement(new AssignStatement("z", new NumberExpression(3)), new AssignStatement("w", new NumberExpression(4)))
    );

    CompoundStatement compoundStatement3 = new CompoundStatement(compoundStatement1, compoundStatement2);

    CompoundStatement compoundStatement4 = new CompoundStatement(compoundStatement3,
            new PrintStatement(new ArithmeticExpression(new VariableExpression("v"), new NumberExpression(10), "*")));


    @Test
    public void testRepeat() throws ControllerExecutionException {
        ProgramStateController programStateController = new ProgramStateController(new ProgramStateRepository("log-file-repeatTest.log"));

        programStateController.addProgramStatement(compoundStatement4);
        programStateController.allStep();
        System.out.println(programStateController.getLogFileContent());
    }

}