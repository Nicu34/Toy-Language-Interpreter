package interpretor.view;

import interpretor.controller.ProgramStateController;
import interpretor.model.expression.*;
import interpretor.model.statement.*;
import interpretor.model.statement.heap.NewStatement;
import interpretor.model.statement.heap.WriteHeapStatement;
import interpretor.model.statement.io.OpenRFileStatement;
import interpretor.model.statement.io.ReadFileStatement;
import interpretor.repository.ProgramStateRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by MuresanN on 11/20/2016.
 */
public class ViewUtils {

    public static ObservableList<Command> populateListOfPrograms() {
        IStatement ex1 = new CompoundStatement(new OpenRFileStatement("fileMultipleIntegers", "test-1"),
                new CompoundStatement(new ReadFileStatement(new VariableExpression("fileMultipleIntegers"), "variable"),
                        new PrintStatement(new VariableExpression("variable"))));
        ProgramStateController programStateController1 = new ProgramStateController(new ProgramStateRepository("log-file-ex1"));
        programStateController1.addProgramStatement(ex1);

        IStatement ex2 = new CompoundStatement(new OpenRFileStatement("fileOneInteger", "test-2"),
                new CompoundStatement(new ReadFileStatement(new VariableExpression("fileOneInteger"), "variable"),
                        new PrintStatement(new VariableExpression("variable"))));
        ProgramStateController programStateController2 = new ProgramStateController(new ProgramStateRepository("log-file-ex2"));
        programStateController2.addProgramStatement(ex2);

        IStatement ex3 = new CompoundStatement(new AssignStatement("variable", new ArithmeticExpression(new NumberExpression(10), new NumberExpression(5), "*")), new PrintStatement(new VariableExpression("variable")));
        ProgramStateController programStateController3 = new ProgramStateController(new ProgramStateRepository("log-file-ex3"));
        programStateController3.addProgramStatement(ex3);


        CompoundStatement compoundWhileStatement = new CompoundStatement(
                new AssignStatement("start", new ArithmeticExpression(new VariableExpression("start"), new NumberExpression(1), "+")),
                new AssignStatement("startValue", new VariableExpression("start")));
        WhileStatement whileStatement = new WhileStatement(new BooleanExpression(new VariableExpression("start"),
                new VariableExpression("end"), "<"), compoundWhileStatement);
        AssignStatement assignStatementStartVariable = new AssignStatement("start", new NumberExpression(0));
        AssignStatement assignStatementEndVariable = new AssignStatement("end", new NumberExpression(10));
        CompoundStatement compoundAssignStatement = new CompoundStatement(assignStatementStartVariable, assignStatementEndVariable);
        CompoundStatement compoundWhile = new CompoundStatement(compoundAssignStatement, whileStatement);
        PrintStatement printStatement = new PrintStatement(new VariableExpression("startValue"));
        CompoundStatement ex4 = new CompoundStatement(compoundWhile, printStatement);
        ProgramStateController programStateController4 = new ProgramStateController(new ProgramStateRepository("log-file-ex4"));
        programStateController4.addProgramStatement(ex4);

        CompoundStatement ex5 = new CompoundStatement(new NewStatement("variable", new NumberExpression(10)),
                new PrintStatement(new VariableExpression("variable")));
        ProgramStateController programStateController5 = new ProgramStateController(new ProgramStateRepository("log-file-ex5"));
        programStateController5.addProgramStatement(ex5);


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
        CompoundStatement compoundStatement4 = new CompoundStatement(compoundStatement3, new AssignStatement("x", new NumberExpression(66)));

        ForkStatement forkStatement = new ForkStatement(compoundStatement4);
        CompoundStatement compoundStatement5 =
                new CompoundStatement(
                        new PrintStatement(new VariableExpression("v")),
                        new PrintStatement(new ReadHeapExpression("a"))
                );
        CompoundStatement compoundStatement6 =
                new CompoundStatement(compoundStatement,
                        forkStatement);
        CompoundStatement ex6 =
                new CompoundStatement(compoundStatement6, compoundStatement5);
        ProgramStateController programStateController6 = new ProgramStateController(new ProgramStateRepository("log-file-ex6"));
        programStateController6.addProgramStatement(ex6);

        /*
        new(v1,2);new(v2,3);new(v3,4);newLatch(cnt,rH(v2));
        fork(wh(v1,rh(v1)*10));print(rh(v1));countDown(cnt));
        fork(wh(v2,rh(v2)*10));print(rh(v2));countDown(cnt));
        fork(wh(v3,rh(v3)*10));print(rh(v3));countDown(cnt));
        await(cnt);
        print(cnt);
        countDown(cnt);
        print(cnt)
         */

        CompoundStatement compoundStatement7 =
                new CompoundStatement(new NewStatement("v1", new NumberExpression(2)),
                        new NewStatement("v2", new NumberExpression(3)));
        CompoundStatement compoundStatement8 =
                new CompoundStatement(compoundStatement7, new NewStatement("v3", new NumberExpression(4)));
        NewLatchStatement newLatchStatement =
                new NewLatchStatement("cnt", new ReadHeapExpression("v2"));
        CompoundStatement compoundStatement9 = new CompoundStatement(compoundStatement8, newLatchStatement);
        ForkStatement forkStatement1 = new ForkStatement(new CompoundStatement(
                new WriteHeapStatement("v1", new ArithmeticExpression(new ReadHeapExpression("v1"), new NumberExpression(10), "*")),
                new CompoundStatement(
                        new PrintStatement(new ReadHeapExpression("v1")),
                        new CountdownStatement("cnt")
                )
        ));

        ForkStatement forkStatement2 = new ForkStatement(new CompoundStatement(
                new WriteHeapStatement("v2", new ArithmeticExpression(new ReadHeapExpression("v2"), new NumberExpression(10), "*")),
                new CompoundStatement(
                        new PrintStatement(new ReadHeapExpression("v2")),
                        new CountdownStatement("cnt")
                )
        ));

        ForkStatement forkStatement3 = new ForkStatement(new CompoundStatement(
                new WriteHeapStatement("v3", new ArithmeticExpression(new ReadHeapExpression("v3"), new NumberExpression(10), "*")),
                new CompoundStatement(
                        new PrintStatement(new ReadHeapExpression("v3")),
                        new CountdownStatement("cnt")
                )
        ));

        CompoundStatement compoundStatement10 = new CompoundStatement(
                new CompoundStatement(compoundStatement9, forkStatement1),
                new CompoundStatement(forkStatement2, forkStatement3)
        );

        CompoundStatement compoundStatement11 = new CompoundStatement(
                new CompoundStatement(new AwaitStatement("cnxt"), new PrintStatement(new VariableExpression("cnt"))),
                new CompoundStatement(new CountdownStatement("cnt"), new PrintStatement(new VariableExpression("cnt")))
        );

        CompoundStatement testProblem1Statement = new CompoundStatement(compoundStatement10, compoundStatement11);
        ProgramStateController programStateControllerExam = new ProgramStateController(new ProgramStateRepository("log-file-ex7"));
        programStateControllerExam.addProgramStatement(testProblem1Statement);




        ObservableList<Command> observableList = FXCollections.observableArrayList();

        observableList.add(new RunExample(ex1.toString(), programStateController1));
        observableList.add(new RunExample(ex2.toString(), programStateController2));
        observableList.add(new RunExample(ex3.toString(), programStateController3));
        observableList.add(new RunExample(ex4.toString(), programStateController4));
        observableList.add(new RunExample(ex5.toString(), programStateController5));
        observableList.add(new RunExample(ex6.toString(), programStateController6));
        observableList.add(new RunExample(programStateControllerExam.toString(), programStateControllerExam));

        return observableList;
    }
}
