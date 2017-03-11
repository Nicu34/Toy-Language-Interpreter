package interpretor.model.statement;

import interpretor.collection.dictionary.IDictionary;
import interpretor.collection.list.IList;
import interpretor.controller.ProgramStateController;
import interpretor.exceptions.model.expression.ExpressionException;
import interpretor.model.ProgramState;
import interpretor.model.expression.NumberExpression;
import interpretor.model.expression.ReadHeapExpression;
import interpretor.model.expression.VariableExpression;
import interpretor.model.statement.heap.NewStatement;
import interpretor.model.statement.heap.WriteHeapStatement;
import interpretor.repository.ProgramStateRepository;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by nicu on 12/23/2016.
 */
public class ForkStatementTest {
//    Example:
//    v=10;new(a,22);
//    fork(wH(a,30);v=32;print(v);print(rH(a)));
//    print(v);print(rH(a))
//    Id=1
//    SymTable_1={v->10,a->1}
//    Id=10
//    SymTable_10={v->32,a->1}
//    Heap={1->30}
//    Out={10,30,32,30}

    @Test
    public void test() throws Exception {
        CompoundStatement compoundStatement =
                new CompoundStatement(
                        new AssignStatement("v", new NumberExpression(10)),
                        new NewStatement("a", new NumberExpression(22)));
        CompoundStatement compoundStatement1 =
                new CompoundStatement(
                        new WriteHeapStatement("a", new NumberExpression(30)),
                        new AssignStatement("v", new NumberExpression(32))
                );
        CompoundStatement compoundStatement2 =
                new CompoundStatement(
                        compoundStatement1, new PrintStatement(new VariableExpression("v"))
                );
        CompoundStatement compoundStatement3 =
                new CompoundStatement(
                        compoundStatement2, new PrintStatement(new ReadHeapExpression("a"))
                );
        ForkStatement forkStatement = new ForkStatement(compoundStatement3);
        CompoundStatement compoundStatement4 =
                new CompoundStatement(
                        new PrintStatement(new VariableExpression("v")),
                        new PrintStatement(new ReadHeapExpression("a"))
                );
        CompoundStatement compoundStatement5 =
                new CompoundStatement(compoundStatement,
                        forkStatement);
        CompoundStatement compoundStatement6 =
                new CompoundStatement(compoundStatement5, compoundStatement4);

        ProgramStateRepository programStateRepository = new ProgramStateRepository("log-file-fork-test");
        ProgramStateController programStateController = new ProgramStateController(programStateRepository);
        programStateController.addProgramStatement(compoundStatement6);

        programStateController.allStep();
    }
}