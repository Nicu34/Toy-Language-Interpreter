package interpretor.model.statement;

import interpretor.collection.dictionary.IDictionary;
import interpretor.collection.stack.IStack;
import interpretor.exceptions.model.expression.ExpressionException;
import interpretor.exceptions.model.statement.StatementException;
import interpretor.exceptions.model.statement.StatementExecutionException;
import interpretor.model.ProgramState;
import interpretor.model.expression.IExpression;

/**
 * Created by MuresanN on 12/6/2016.
 */
public class WhileStatement implements IStatement {

    private IExpression expression;

    private IStatement statement;

    private boolean not = false;

    public WhileStatement(IExpression expression, IStatement statement) {
        this.expression = expression;
        this.statement = statement;
    }

    public WhileStatement(IExpression expression, IStatement statement, boolean not) {
        this.expression = expression;
        this.statement = statement;
        this.not = not;
    }

    public IExpression getExpression() {
        return expression;
    }

    public void setExpression(IExpression expression) {
        this.expression = expression;
    }

    public IStatement getStatement() {
        return statement;
    }

    public void setStatement(IStatement statement) {
        this.statement = statement;
    }

    public void setNot() {
        this.not = !this.not;
    }

    public ProgramState execute(ProgramState programState) throws StatementException {
        IDictionary<Integer, Integer> heapTable = programState.getHeapTable();
        IDictionary<String, Integer> symbolTable = programState.getSymbolTable();
        Integer expressionResult;

        try {
            expressionResult = expression.evaluate(symbolTable, heapTable);
        } catch (ExpressionException e) {
            throw new StatementExecutionException(e.getMessage());
        }

        if ((this.not == false && expressionResult.equals(1)) ||(this.not == true && !expressionResult.equals(1))) {
            IStack<IStatement> stack = programState.getExecutionStack();

            stack.push(this);
            programState.setExecutionStack(stack);
            statement.execute(programState);
        }

        return null;
    }

    @Override
    public String toString() {
        return "while ( " + expression + " ) { " + statement + " }";
    }
}
